CREATE OR REPLACE
PACKAGE pagingPackage
AS
type paging_cursor
IS
  ref
  CURSOR;
  END pagingPackage;

CREATE OR REPLACE
PROCEDURE Report_Paging(
    pageStart  IN NUMBER,
    pageSize   IN NUMBER,
    tableName  IN VARCHAR2,
    strWhere   IN VARCHAR2,
    strOrderby IN VARCHAR2,
    totalCount OUT NUMBER,
    queryResult OUT pagingPackage.paging_cursor )
IS
  countSql  VARCHAR2(2000);
  pageSql   VARCHAR2(4000);
  pageCount NUMBER;
  row_start NUMBER;
  row_end   NUMBER;
BEGIN
  --查询总条数
  countSql    := 'select count(1) from ' || tableName;
  IF strWhere IS NOT NULL OR strWhere <> '' THEN
    countSql  := countSql || ' where 1=1 ' || strWhere;
  END IF;
  EXECUTE immediate countSql INTO totalCount;
  --分页计算
  pageCount := totalCount / pageSize + 1;
  row_start := (pageStart -1 ) * pageSize + 1;
  row_end   := pageStart  * pageSize;
  --查询记录集
  pageSql     := 'select rownum ro, t.* from ' || tableName || ' t';
  pageSql     := pageSql || ' where rownum<=' || row_end;
  IF strWhere IS NOT NULL OR strWhere <> '' THEN
    pageSql   := pageSql || strWhere;
  END IF;
  IF strOrderby IS NOT NULL OR strOrderby <> '' THEN
    pageSql     := pageSql || ' order by ' || strOrderby;
  END IF;
  pageSql := 'select * from (' || pageSql || ') where ro >= ' || row_start;
  OPEN queryResult FOR pageSql;
END;
  