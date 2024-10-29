class hana
{
public static void main(String []args)
  {
  String orig_mnt = "mnt0001";
  String orig_vol = "hdb0003";
  String new_mnt = "mnt0001";
  String new_vol = "hdb0002";

  String build_stmt = "SELECT 'ALTER SYSTEM ALTER CONFIGURATION (\"topology.ini\", \"system\") SET (' || '\"' ||" +
        " replace(path,'/volumes/%s:%s','/volumes/%s:%s') || '\"' , ".formatted(orig_mnt,orig_vol,new_mnt,new_vol) +
        "'\"' || name || '\") = ' || '\"' || " +
        "replace(value,'%s.%s','%s.%s') || '\";' from M_TOPOLOGY_TREE ".formatted(orig_mnt,orig_vol,new_mnt,new_vol) +
        "WHERE path IN " +
        "( " +
        "    SELECT path FROM M_TOPOLOGY_TREE " +
        "    WHERE value LIKE LTRIM " +
        "    ( " +
        "        ( " +
        "            SELECT path from M_TOPOLOGY_TREE " +
        "            WHERE value = UCASE('?') " +
        "            AND path like '/databases/%'),'/database/' " +
        "    ) " +
        "    AND path = '/volumes/%s:%s' ".formatted(orig_mnt,orig_vol) +
        ");";
        System.out.println(build_stmt);
    }
};
