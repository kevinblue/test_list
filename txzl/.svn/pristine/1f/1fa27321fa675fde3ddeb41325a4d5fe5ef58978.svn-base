/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.authentication;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;


/**
 * An {@link org.springframework.security.core.Authentication} implementation that is designed for simple presentation of a
 * username and password.
 * <p>
 * The <code>principal</code> and <code>credentials</code> should be set with an <code>Object</code> that provides
 * the respective property via its <code>Object.toString()</code> method. The simplest such <code>Object</code> to use
 * is <code>String</code>.
 *
 * @author Ben Alex
 * @version $Id: UsernamePasswordAuthenticationToken.java 3593 2009-04-22 04:11:38Z ltaylor $
 */
public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {
    //~ Instance fields ================================================================================================

    
	 /**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 **/
	
	private static final long serialVersionUID = 1L;
	
	private final Object credentials;
    private final Object principal;
    private boolean isLoginSSO = false;
    //~ Constructors ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link
     * #isAuthenticated()} will return <code>false</code>.
     *
     */
    public UsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.setLoginSSO(isLoginSSO);
        setAuthenticated(false);
    }

    /**
     * @deprecated use the list of authorities version
     */
    public UsernamePasswordAuthenticationToken(Object principal, Object credentials, GrantedAuthority[] authorities) {
        this(principal, credentials, Arrays.asList(authorities));
    }

    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
     * implementations that are satisfied with producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     * @param credentials
     * @param authorities
     */
    public UsernamePasswordAuthenticationToken(Object principal, Object credentials, List<GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }


    //~ Methods ========================================================================================================

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

	
	 /**
	 * @param isLoginSSO the isLoginSSO to set
	 **/
	
	public void setLoginSSO(boolean isLoginSSO) {
		this.isLoginSSO = isLoginSSO;
	}

	
	 /**
	 * isLoginSSO
	 * @return the isLoginSSO
	 * @since 1.0.0
	 **/
	
	public boolean isLoginSSO() {
		return isLoginSSO;
	}
}
