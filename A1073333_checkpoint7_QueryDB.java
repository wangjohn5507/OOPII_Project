import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class A1073333_checkpoint7_QueryDB {
    //Description : the driver description of mysql
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //Description : the protocol description of mysql
    private static final String PROTOCOL = "jdbc:mysql://140.127.220.220:3306/";
    //Description : the obstacle set queried from database.
    private static ArrayList<Integer[]> data;
    //Description : the filename of obstacle image queried from database.
    private static HashMap<Integer, String> typeChar;
    //Description : the ID of the map in database;
    private static String mapID;
    //Description : the sand set queried from database.
    private static ArrayList<Integer[]> sands;
    public A1073333_checkpoint7_QueryDB(String mapID){
        this.data  = new ArrayList<Integer[]>();
        this.sands = new ArrayList<Integer[]>();
        this.typeChar = new HashMap<Integer, String>();
        this.mapID = mapID;
        queryData(this.data, this.typeChar);
        querySand(this.sands);
    }

    private static void queryData(ArrayList<Integer[]> data,HashMap<Integer, String> typeChar) {
    /*********************************The Past TODO (Checkpoint2)********************************
     * 
     * TODO(Past) Querying the barrier location from the server, and set it into Arraylist.
     * 
     * TODO(Past) Querying the bar_type and the corresponding file_name from the server, and set it into HashMap.
     * 
     * Hint:  for ckp2 to after, you need replace querying  column "file_name" with querying  column "display". 
     * 
     **********************************The End of the TODO***************************************/

     /********************************************************************************************
     START OF YOUR CODE
     ********************************************************************************************/
    try{
        Class.forName(DRIVER).newInstance();
    }
    catch(Exception err){
        err.printStackTrace(System.err);
        System.exit(0);
    }
    String dbName = "CHECKPOINT";
    Connection conn = null;
    try{
        conn = DriverManager.getConnection(PROTOCOL+dbName,"checkpoint","ckppwd");
        Statement s = conn.createStatement();
        // ResultSet rs = null;
        // rs=s.executeQuery("SELECT * FROM map WHERE map_id="+mapID);
        // while(rs.next())
        // {
        //     HEIGHT = rs.getInt("height");
        //     WIDTH = rs.getInt("width");
        // }
        // rs.close();
        
        ResultSet rs1 = null;
        rs1=s.executeQuery("SELECT * FROM barrier WHERE map_id="+mapID);
        while(rs1.next())
        {
            int row = rs1.getInt("row_idx");
            int column = rs1.getInt("column_idx");
            int bartype = rs1.getInt("bar_type");
            Integer[] a = new Integer[5];
            a[0]=row;
            a[1]=column;
            a[2]=bartype;
            data.add(a);
        }
        rs1.close();

        ResultSet rs2=null;
        rs2=s.executeQuery("SELECT * FROM barrier_type");
        while(rs2.next())
        {
            String filename=rs2.getString("file_name");
            int bartype1=rs2.getInt("bar_type");
            typeChar.put(bartype1,filename);
        }
        rs2.close();
    }
    catch(SQLException err){
        System.err.println("SQL error.");
        err.printStackTrace(System.err);
        System.exit(0);
    }

    /********************************************************************************************
     END OF YOUR CODE
     ********************************************************************************************/
    }
    private static void querySand(ArrayList<Integer[]> sands) {
    /*********************************The TODO This Time (Checkpoint7)***************************
     * 
     * TODO(1) Querying the map size and the sand location from the server, and set it into Arraylist.
     * 
     **********************************The End of the TODO***************************************/

     /********************************************************************************************
     START OF YOUR CODE
     ********************************************************************************************/
    try{
        Class.forName(DRIVER).newInstance();
    }
    catch(Exception err){
        err.printStackTrace(System.err);
        System.exit(0);
    }
    String dbName = "CHECKPOINT";
    Connection conn = null;
    try{
        conn = DriverManager.getConnection(PROTOCOL+dbName,"checkpoint","ckppwd");
        Statement s = conn.createStatement();
        // ResultSet rs = null;
        // rs=s.executeQuery("SELECT * FROM map WHERE map_id="+mapID);
        // while(rs.next())
        // {
        //     HEIGHT = rs.getInt("height");
        //     WIDTH = rs.getInt("width");
        // }
        // rs.close();
        
        ResultSet rs1 = null;
        rs1=s.executeQuery("SELECT * FROM sand WHERE map_id="+mapID);
        while(rs1.next())
        {
            int row = rs1.getInt("row_idx");
            int column = rs1.getInt("column_idx");
            int sandid = rs1.getInt("sand_id");
            Integer[] a = new Integer[5];
            a[0]=row;
            a[1]=column;
            a[2]=sandid;
            sands.add(a);
        }
        rs1.close();
    }
    catch(SQLException err){
        System.err.println("SQL error.");
        err.printStackTrace(System.err);
        System.exit(0);
    }


    /********************************************************************************************
     END OF YOUR CODE
    ********************************************************************************************/
    }
    public ArrayList getObstacle(){
        return this.data;
    }
    public void setObstacle(ArrayList<Integer[]> data){
        this.data = data;
    }
    public String getMapID(){
        return this.mapID;
    }
    public void setMapID(String mapID){
        this.mapID = mapID;
    }
    public HashMap getObstacleImg(){
        return this.typeChar;
    }
    public void setObstacleImg(HashMap<Integer, String> typeChar){
        this.typeChar = typeChar;
    }
    public ArrayList getSands(){
        return this.sands;
    }
    public void setSands(ArrayList<Integer[]> sands){
        this.sands = sands;
    }
}
