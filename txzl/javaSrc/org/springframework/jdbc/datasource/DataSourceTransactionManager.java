package org.springframework.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SuppressWarnings("serial")
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager
    implements ResourceTransactionManager, InitializingBean
{
    private static class DataSourceTransactionObject extends JdbcTransactionObjectSupport
    {

        public void setConnectionHolder(ConnectionHolder connectionHolder, boolean newConnectionHolder)
        {
            super.setConnectionHolder(connectionHolder);
            this.newConnectionHolder = newConnectionHolder;
        }

        public boolean isNewConnectionHolder()
        {
            return newConnectionHolder;
        }

        @SuppressWarnings("unused")
		public boolean hasTransaction()
        {
            return getConnectionHolder() != null && getConnectionHolder().isTransactionActive();
        }

        public void setMustRestoreAutoCommit(boolean mustRestoreAutoCommit)
        {
            this.mustRestoreAutoCommit = mustRestoreAutoCommit;
        }

        public boolean isMustRestoreAutoCommit()
        {
            return mustRestoreAutoCommit;
        }

        public void setRollbackOnly()
        {
            getConnectionHolder().setRollbackOnly();
        }

        public boolean isRollbackOnly()
        {
            return getConnectionHolder().isRollbackOnly();
        }

        private boolean newConnectionHolder;
        private boolean mustRestoreAutoCommit;

        private DataSourceTransactionObject()
        {
        }

        DataSourceTransactionObject(DataSourceTransactionObject datasourcetransactionobject)
        {
            this();
        }
    }


    public DataSourceTransactionManager()
    {
        setNestedTransactionAllowed(true);
    }

    public DataSourceTransactionManager(DataSource dataSource)
    {
        this();
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    public void setDataSource(DataSource dataSource)
    {
        if(dataSource instanceof TransactionAwareDataSourceProxy)
            this.dataSource = ((TransactionAwareDataSourceProxy)dataSource).getTargetDataSource();
        else
            this.dataSource = dataSource;
    }

    public DataSource getDataSource()
    {
        return dataSource;
    }

    public void afterPropertiesSet()
    {
        if(getDataSource() == null)
            throw new IllegalArgumentException("Property 'dataSource' is required");
        else
            return;
    }

    public Object getResourceFactory()
    {
        return getDataSource();
    }

    protected Object doGetTransaction()
    {
        DataSourceTransactionObject txObject = new DataSourceTransactionObject(null);
        txObject.setSavepointAllowed(isNestedTransactionAllowed());
        ConnectionHolder conHolder = (ConnectionHolder)TransactionSynchronizationManager.getResource(dataSource);
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

    protected boolean isExistingTransaction(Object transaction)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)transaction;
        return txObject.getConnectionHolder() != null && txObject.getConnectionHolder().isTransactionActive();
    }

    protected void doBegin(Object transaction, TransactionDefinition definition)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)transaction;
        Connection con = null;
        try
        {
            if(txObject.getConnectionHolder() == null || txObject.getConnectionHolder().isSynchronizedWithTransaction())
            {
                Connection newCon = dataSource.getConnection();
                if(logger.isDebugEnabled())
                    logger.debug((new StringBuilder("Acquired Connection [")).append(newCon).append("] for JDBC transaction").toString());
                txObject.setConnectionHolder(new ConnectionHolder(newCon), true);
            }
            txObject.getConnectionHolder().setSynchronizedWithTransaction(true);
            con = txObject.getConnectionHolder().getConnection();
            Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(con, definition);
            txObject.setPreviousIsolationLevel(previousIsolationLevel);
            if(con.getAutoCommit())
            {
                txObject.setMustRestoreAutoCommit(true);
                if(logger.isDebugEnabled())
                    logger.debug((new StringBuilder("Switching JDBC Connection [")).append(con).append("] to manual commit").toString());
                con.setAutoCommit(false);
            }
//            if(definition != null && definition.isReadOnly())
//            {
//            	 //由于oracle提供的JDBC暂不开启只读事务，需手动填写只读事务
//                 logger.info("######################:只读事务"+definition.getName());
//                 PreparedStatement preparedstatement = null;    
//                 String s = "SET TRANSACTION READ ONLY";    
//                 preparedstatement = con.prepareStatement(s);
//                 preparedstatement.execute();               
//                 if(preparedstatement != null)             
//                 preparedstatement.close();             
//            }
            txObject.getConnectionHolder().setTransactionActive(true);
            int timeout = determineTimeout(definition);
            if(timeout != -1)
                txObject.getConnectionHolder().setTimeoutInSeconds(timeout);
            if(txObject.isNewConnectionHolder())
                TransactionSynchronizationManager.bindResource(getDataSource(), txObject.getConnectionHolder());
        }
        catch(SQLException ex)
        {
            DataSourceUtils.releaseConnection(con, dataSource);
            throw new CannotCreateTransactionException("Could not open JDBC Connection for transaction", ex);
        }
    }

    protected Object doSuspend(Object transaction)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)transaction;
        txObject.setConnectionHolder(null);
        ConnectionHolder conHolder = (ConnectionHolder)TransactionSynchronizationManager.unbindResource(dataSource);
        return conHolder;
    }

    protected void doResume(Object transaction, Object suspendedResources)
    {
        ConnectionHolder conHolder = (ConnectionHolder)suspendedResources;
        TransactionSynchronizationManager.bindResource(dataSource, conHolder);
    }

    protected void doCommit(DefaultTransactionStatus status)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        if(status.isDebug())
            logger.debug((new StringBuilder("Committing JDBC transaction on Connection [")).append(con).append("]").toString());
        try
        {
            con.commit();
        }
        catch(SQLException ex)
        {
            throw new TransactionSystemException("Could not commit JDBC transaction", ex);
        }
    }

    protected void doRollback(DefaultTransactionStatus status)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)status.getTransaction();
        Connection con = txObject.getConnectionHolder().getConnection();
        if(status.isDebug())
            logger.debug((new StringBuilder("Rolling back JDBC transaction on Connection [")).append(con).append("]").toString());
        try
        {
            con.rollback();
        }
        catch(SQLException ex)
        {
            throw new TransactionSystemException("Could not roll back JDBC transaction", ex);
        }
    }

    protected void doSetRollbackOnly(DefaultTransactionStatus status)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)status.getTransaction();
        if(status.isDebug())
            logger.debug((new StringBuilder("Setting JDBC transaction [")).append(txObject.getConnectionHolder().getConnection()).append("] rollback-only").toString());
        txObject.setRollbackOnly();
    }

    protected void doCleanupAfterCompletion(Object transaction)
    {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject)transaction;
        if(txObject.isNewConnectionHolder())
            TransactionSynchronizationManager.unbindResource(dataSource);
        Connection con = txObject.getConnectionHolder().getConnection();
        try
        {
            if(txObject.isMustRestoreAutoCommit())
                con.setAutoCommit(true);
            DataSourceUtils.resetConnectionAfterTransaction(con, txObject.getPreviousIsolationLevel());
        }
        catch(Throwable ex)
        {
            logger.debug("Could not reset JDBC Connection after transaction", ex);
        }
        if(txObject.isNewConnectionHolder())
        {
            if(logger.isDebugEnabled())
                logger.debug((new StringBuilder("Releasing JDBC Connection [")).append(con).append("] after transaction").toString());
            DataSourceUtils.releaseConnection(con, dataSource);
        }
        txObject.getConnectionHolder().clear();
    }
    private DataSource dataSource;
}