import java.util.*;

/***********************************  Hint ********************************************
 * 
 * To finish the object, we define some variables in this code.
 * 
 * 1. Absolute Location --> The GUI location in Swing definition.
 * 2. Grid Location (GUI version) --> We divided the map into 16*16 grids, 
 *    use this variable to record the certain location of a grid. 
 * 3. Delta X/Y --> The x,y location offsets of the map in the panel. 
 *    We calculate these two scalars by substracting the center point of the map (width/2,height/2) 
 *    and the current panel center point (CenterX, CenterY) in panel. 
 * 4. Relative Location --> The x,y location for painting the objects (e.g. obstacle and players) on the panel.
 *    We calculate this using Delta X/Y plus the grid location mutiply the length of the grid (Absolute Coordinate).
 * 
 **********************************The End of the Hint***************************************/

public class A1073333_checkpoint7_PlayerMovement implements Runnable{
    //Description : the player object.
    private A1073333_checkpoint7_Player player;
    //Description : the panel object. 
    private A1073333_checkpoint7_DemoPanel panel;
    //Description : while moving the player, it needs to move by.(in Grid Location)
    private A1073333_checkpoint7_RouteLinkedList route;
    //Description : the X absolute location the destination is on.
    private int newPointX;
    //Description : the Y absolute location the destination is on.
    private int newPointY;
    //Description : the time that the player moving in per step
    private int milliseconds; 
    //Description : the path that the player has walked through.
    private ArrayList<A1073333_checkpoint7_Block> walkedPath;
    //Description : UCS : 2, BFS : 1, DFS : 0
    private int algorithm;
    //Description : the map with all blocks. (a. k. a. state space)
    //You can get the location block you want with typing map[x][y].
    private A1073333_checkpoint7_Block[][] map;
    //Description : record the block and it's ParentBlock.
    private HashMap<A1073333_checkpoint7_Block, A1073333_checkpoint7_Block> ParentBlock;
    //Description : record the block cost;
    private HashMap<A1073333_checkpoint7_Block, Integer> PathCost;
    //Description : record the block that has be visited.
    private boolean[][] visited;
    //Description : the player current location ( the root of the tree).
    private A1073333_checkpoint7_Block root;
    //Description : if algorithm equals 0 means using DFS.
    private final int DFS = 0;
    //Description : if algorithm equals 1 means using BFS.
    private final int BFS = 1;
    //Description : if algorithm equals 1 means using UCS.
    private final int UCS = 2;
    public A1073333_checkpoint7_PlayerMovement(A1073333_checkpoint7_Player player,A1073333_checkpoint7_DemoPanel panel,int newPointX,int newPointY, int milliseconds, int algorithm, A1073333_checkpoint7_Block[][] map) {
        this.player = player;
        this.panel = panel;
        this.newPointX = newPointX;
        this.newPointY = newPointY;
        this.milliseconds = milliseconds;
        this.route = new A1073333_checkpoint7_RouteLinkedList();
        this.walkedPath = new ArrayList<A1073333_checkpoint7_Block>();
        this.algorithm = algorithm;
        this.map = map;
        this.ParentBlock = new HashMap<A1073333_checkpoint7_Block, A1073333_checkpoint7_Block>();
        this.PathCost = new HashMap<A1073333_checkpoint7_Block, Integer>();
        this.visited = new boolean[panel.getXGrids()][panel.getYGrids()];
        this.root = map[player.getMapX()][player.getMapY()];
        for(int x =0 ; x<panel.getXGrids(); x++ ){
            for(int y =0 ; y<panel.getYGrids(); y++ ){
                visited[x][y] = false;
            }
        }
    }

    //Description : The Thread object running function(The thing that this thread doing ).
    @Override
    public void run() {

        /*********************************The Past TODO (Checkpoint6)********************************
         * 
         * TODO(1.1): This time you have need to calculate the target location (Grid verb.) first 
         *            and get the target block.
         * TODO(1.2): After you find the target block, you have to set target in panel via panel.setTarget() 
         *            and repaint it in order to display which block you clicked.
         * TODO(1.3): generate the route via detectWay().
         * TODO(1.4): follow the route via followRoute();
         * TODO(1.5): After following the route, you have to update the player's status.(player.setOnClick(), 
         *            player.setMoving()) and clear the path that the player has walked through via panel.setPath()
         *            and repaint the panel.
         * 
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
        int X = (newPointX-(panel.getWidth()/2-panel.getCenterX()))/panel.getGridLen();
         int Y = (newPointY-(panel.getHeight()/2-panel.getCenterY()))/panel.getGridLen();
         if(X>=0&&Y>=0&&X<panel.getXGrids()&&Y<panel.getYGrids()){
            A1073333_checkpoint7_Block target = map[X][Y];
            panel.setTarget(target);
            panel.repaint();
            detectWay(root, target, ParentBlock, visited);
            followRoute(route);
         }
         player.setOnClick(false);
         player.setMoving(false);
         panel.setPath(new ArrayList<A1073333_checkpoint7_Block>());
         panel.repaint();
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }

    
    private A1073333_checkpoint7_RouteLinkedList detectWay(A1073333_checkpoint7_Block root,A1073333_checkpoint7_Block target, HashMap<A1073333_checkpoint7_Block, A1073333_checkpoint7_Block> ParentBlock, boolean[][] visited){
        /**********************************The TODO This Time (Checkpoint7)**************************
         * 
         * TODO(Past): For the TODO here, you have to Search the target block via the function Search() 
         *             and back trace and generate the route by the function createRoute();
         * TODO(1.1): For the TODO here, add the case that the algorithm is UCS.
         * 
         * Hint(1): The BFS algorithm needs to use the queue to work, so we make a object named BlockQueue
         *          for BFS.
         * Hint(2): The DFS algorithm needs to use the stack to work, so we make a object named BlockStack
         *          for DFS.
         * Hint(3): These two object are all implemnt the fringe, the detail description can be found 
         *          in the code of Fringe.java BlockQueue.java BlockStack.java.
         * Hint(4): Before send the fringe into the function Search(), you have to add the root (the 
         *          player current location) into fringe.
         * Hint(5): (New) The UCS algorithm needs to use the priorityQueue to work, so we make a object named 
         *          BlockPriorityQueue for UCS.
         * Hint(6): (New) To calculate the priority, you have to implement a Comparator<block> object and make 
         *          it as an input in the constructor of BlockPriorityQueue.
         * Hint(7): (New) Before starting the searching, you need to initialize the PathCost and set the root with
         *          its cost.
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        
        if(algorithm==1){//BFS
            A1073333_checkpoint7_BlockQueue BlockQueue = new A1073333_checkpoint7_BlockQueue();
            A1073333_checkpoint7_Block target1;
            BlockQueue.add(root);
            // System.out.println("target:"+target.getX()+""+target.getY());
            target1=Search(BlockQueue, target, ParentBlock, visited);
            // System.out.println("ParentBlock:"+ParentBlock);
            if(target1!=null)
            route = createRoute(ParentBlock, target);
            return route;
        }
        if(algorithm==0){//DFS
            A1073333_checkpoint7_BlockStack BlockStack = new A1073333_checkpoint7_BlockStack();
            A1073333_checkpoint7_Block target0;
            BlockStack.add(root);
            target0=Search(BlockStack, target, ParentBlock, visited);
            if(target0!=null)
            route = createRoute(ParentBlock, target);
            return route;
        }
        if(algorithm==2){//UCS
            class myComparator implements Comparator<A1073333_checkpoint7_Block>{
                @Override
                public int compare(A1073333_checkpoint7_Block b1, A1073333_checkpoint7_Block b2){
                 return PathCost.get(b1)-PathCost.get(b2);
                }
            }
            Comparator<A1073333_checkpoint7_Block> c = new myComparator();
            A1073333_checkpoint7_BlockPriorityQueue pq = new A1073333_checkpoint7_BlockPriorityQueue(c);
            A1073333_checkpoint7_Block target2;
            pq.add(root);
            PathCost.put(root, root.getCost());
            target2 = Search(pq,target,ParentBlock,visited);
            if(target2!=null)
            route = createRoute(ParentBlock,target);
            return route;
        }
        return null;
        
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
        
    }
    public A1073333_checkpoint7_Block Search(A1073333_checkpoint7_Fringe fringe, A1073333_checkpoint7_Block target, HashMap<A1073333_checkpoint7_Block, A1073333_checkpoint7_Block> ParentBlock, boolean[][] visited){
        /*********************************The Past TODO (Checkpoint6)********************************
         * 
         * TODO(Past): For the TODO here, you have to implement the searching funciton;
         * TODO(Past): You MUST print the message of "Searching at (x, y)" in order to let us check if you sucessfully do it.
         * TODO(Past): After you find the target, you just need to return the target block.
         * //System.out.println("Searching at ("+Integer.toString(YOURBLOCK.getX())+", "+Integer.toString(YOURBLOCK.getY())+")");
         * 
         * Hint1: Page 44 in the teaching material "lecture05" may be your reference.
         * Hint2: if the target can not be search you should return null(that means failure).
         * 
         * pseudo code is provided here: 
         *   1. get the block from fringe.
         *   2. print the message
         *   3. if that block equals target return it.
         *   4. if not, expand the block and insert then into fringe.
         *   5. return to 1. until the fringe does not have anyting in it.
         * 
         **********************************The End of the TODO**************************************/
        
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        if(target.getType()=="obstacle")return null;
        while(true){
            if(fringe.isEmpty())return null;
            A1073333_checkpoint7_Block node = fringe.remove();
            System.out.println("Searching at ("+Integer.toString(node.getX())+", "+Integer.toString(node.getY())+")");
            if(node.getX()==target.getX()&&node.getY()==target.getY()){
                return node;
            }
            ArrayList<A1073333_checkpoint7_Block> expansion = expand(node, ParentBlock, visited);
            int size = expansion.size();
            for(int i =0; i <size;i++){
                fringe.add(expansion.get(i));
            }
        }

        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    private ArrayList<A1073333_checkpoint7_Block> expand(A1073333_checkpoint7_Block currentBlock, HashMap<A1073333_checkpoint7_Block, A1073333_checkpoint7_Block> ParentBlock, boolean[][] visited){
        /*********************************The TODO This Time (Checkpoint7)*****************************
         * 
         * TODO(Past): For the TODO here, you have to implement the expand funciton and return the Blocks(successor);
         * TODO(Past): the order that you expand is North(Up) West(Left) South(Down) East(Right).
         * TODO(Past): before adding the block into successor, you have to check if it is valid by checkBlock().
         * TODO(2.1): For the TODO here, you have to calculate the cost of the path that the player walked from root 
         *          to new blocks and set it into the HashMap pathCost.
         * 
         * Hint1: Page 44 in the teaching material "lecture05" may be your reference.
         * Hint2: While the block is valid, before you add the block into successor, 
         *        you should set its ParentBlock (We prepare a HashMap to implement this).
         *        And you should also set it is visited. (We prepare 2D boolean array for you) (the (x,y) block <--> visited[x][y] )
         * Hint3: You can get the cost of the block via Block.getCost().
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        ArrayList<A1073333_checkpoint7_Block> successor = new ArrayList<A1073333_checkpoint7_Block>();
        if(checkBlock(currentBlock.getX(), currentBlock.getY()-1, visited)){
            A1073333_checkpoint7_Block s = map[currentBlock.getX()][currentBlock.getY()-1];
            ParentBlock.put(s,currentBlock);
            visited[currentBlock.getX()][currentBlock.getY()-1]=true;
            if(algorithm==UCS){
                int cost = PathCost.get(currentBlock);
                cost = cost+s.getCost();
                PathCost.put(s, cost);
            }
            successor.add(s);
            // System.out.println(s.getX());
        }
        if(checkBlock(currentBlock.getX()-1, currentBlock.getY(), visited)){
            A1073333_checkpoint7_Block s = map[currentBlock.getX()-1][currentBlock.getY()];
            ParentBlock.put(s,currentBlock);
            visited[currentBlock.getX()-1][currentBlock.getY()]=true;
            if(algorithm==UCS){
                int cost = PathCost.get(currentBlock);
                cost = cost+s.getCost();
                PathCost.put(s, cost);
            }
            
            successor.add(s);
            // System.out.println(s.getX());
        }
        if(checkBlock(currentBlock.getX(), currentBlock.getY()+1, visited)){
            A1073333_checkpoint7_Block s = map[currentBlock.getX()][currentBlock.getY()+1];
            ParentBlock.put(s,currentBlock);
            visited[currentBlock.getX()][currentBlock.getY()+1]=true;
            if(algorithm==UCS){
                int cost = PathCost.get(currentBlock);
                cost = cost+s.getCost();
                PathCost.put(s, cost);
            }
            successor.add(s);
            // System.out.println(s.getX());
        }
        if(checkBlock(currentBlock.getX()+1, currentBlock.getY(), visited)){
            A1073333_checkpoint7_Block s = map[currentBlock.getX()+1][currentBlock.getY()];
            ParentBlock.put(s,currentBlock);
            visited[currentBlock.getX()+1][currentBlock.getY()]=true;
            if(algorithm==UCS){
                int cost = PathCost.get(currentBlock);
                cost = cost+s.getCost();
                PathCost.put(s, cost);
            }
            successor.add(s);
            // System.out.println(s.getX());
        }
        return successor;

        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    private Boolean checkBlock(int newLocationX, int newLocationY, boolean[][] visited){
        /*********************************The Past TODO (Checkpoint6)********************************
         * 
         * TODO(Past): For the TODO here, you have to implement the checkBlock funciton and return if the Block is valid or not;
         * TODO(Past): First you have to check the new block is in your map or not.
         * TODO(Past): Second the block can not be obstacle.
         * TODO(Past): Third the block should not be visited.
         * 
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        if(newLocationX>panel.getXGrids()-1||newLocationY>panel.getYGrids()-1||newLocationX<0||newLocationY<0){
            return false;
        }
        if(map[newLocationX][newLocationY].getType()=="obstacle"){
            return false;
        }
        if(visited[newLocationX][newLocationY]){
            return false;
        }
        return true;
        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }

    private A1073333_checkpoint7_RouteLinkedList createRoute(HashMap<A1073333_checkpoint7_Block, A1073333_checkpoint7_Block> ParentBlock, A1073333_checkpoint7_Block target){
        /*********************************The Past TODO (Checkpoint6)********************************
         * 
         * TODO(Past): For the TODO here, you have to trace back the route and return the route;
         * 
         * Hint1: You can get the parent block of target by HashMap ParentBlock, thus you can calculate
         * the last step of the route. And then you get the parent block of the parent block of target, 
         * you can calculate the backward step and so on. 
         * 
         * presudo code is provided here:
         *      1. get parent block
         *      2. calculate the delta location
         *      3. insert into head
         *      4. make the target equals its parent block and so on.
         * 
         **********************************The End of the TODO**************************************/

        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1073333_checkpoint7_RouteLinkedList road = new A1073333_checkpoint7_RouteLinkedList();
        int referenceDirection=0;
        int referenceAxis=0;
        while(target!=root){
            A1073333_checkpoint7_Block parent = ParentBlock.get(target);
            int axis;
            int direction;
            if(parent.getX()-target.getX()!=0){
                axis=0;
            }
            else{
                axis=1;
            }
            if((parent.getX()-target.getX()<0)||(parent.getY()-target.getY()<0)){
                direction=1;
            }
            else{
                direction=-1;
            }
            // System.out.println(referenceAxis+" "+referenceDirection+" "+axis+" "+direction);
            road.insert(referenceAxis, referenceDirection, axis, direction);
            referenceAxis=axis;
            referenceDirection=direction;
            target=parent;
        }
        return road;

        /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/

    }
    private void followRoute(A1073333_checkpoint7_RouteLinkedList route){
        
        /*********************************The Past TODO (Checkpoint6)********************************
         * 
         * TODO(Past): This time in order to make moving more easily to trace and debug. We make a ArrayList
         * walkedPath to record the block that the player had walked through. You have to add the block into
         * walkedPath and set it into panel and then the panel will draw it automatically.
         * 
         * pseudo code provide here
         *      loop the route untill it does not have next node. # you have done this last time.
         *      movePlayerOneGrid() (base on the value of route node). # you have done this last time.
         *      add block that the player walk through into walkedPath. # This time you have to implement it.
         *      set the walkedPath in panel by panel.setPath().# This time you have to implement it.
         *      update the player's location # you have done this last time.
         *      checkCollided() # you have done this last time.
         * 
         **********************************The End of the TODO**************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
        A1073333_checkpoint7_Node point=route.getHead();
        // A1073333_checkpoint5_Node find=null;
        int length=route.length();
        for(int i=0;i<length;i++){
            int direction = point.getDirection();
            int axis = point.getAxis();
            movePlayerOneGrid(panel,player,direction,axis,milliseconds);
            
            if(axis==0)
                player.setMapX(player.getMapX()+direction);
            else
                player.setMapY(player.getMapY()+direction);

            walkedPath.add(map[player.getMapX()][player.getMapY()]);
            panel.setPath(walkedPath);
            checkCollided(player.getMapX(), player.getMapY());
            
            if(point.getNext()!=null)point = point.getNext();
            
        }
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
    //Description : Check the new point(Grid location) is collided or not.
    private void checkCollided(Integer newPointX, Integer newPointY){
        if(map[newPointX][newPointY].getType() == "obstacle"){
            A1073333_checkpoint7_BugFrame bugFrame = new A1073333_checkpoint7_BugFrame();
            bugFrame.setVisible(true);
        }
        if(newPointX < 0 || newPointY < 0 || newPointY*this.panel.getGridLen() >= this.panel.getMapHeight() || newPointX*this.panel.getGridLen() >= this.panel.getMapWidth()){
            A1073333_checkpoint7_BugFrame bugFrame = new A1073333_checkpoint7_BugFrame();
            bugFrame.setVisible(true);
        }
    }
    
    //Description : moving the player one gird location via this function.
    private void movePlayerOneGrid(A1073333_checkpoint7_DemoPanel panel,A1073333_checkpoint7_Player player,int direction,int axis, int milliseconds){
        
        for (int i=1;i<=player.getStepTime();i++){
            movePlayerOneStep(panel, player, direction, axis, milliseconds);
        }
    }

    private void movePlayerOneStep(A1073333_checkpoint7_DemoPanel panel,A1073333_checkpoint7_Player player,int direction,int axis, int milliseconds){
        /*********************************The Past TODO (Checkpoint4)********************************
         * 
         * TODO(Past) In this function you have to make your player moving one step here.
         *            You need to continue setting the player or panel location, 
         *            but with stopping a little time in order to make the player moving continusly.
         *            We have already prepared the continus function(movePlayerOneGrid()),
         *            but you have to realize the stopping time and setting the player or panel location here
         *            There is a requirement that the player needs to keep being in the center point of the windows.
         *            So maybe not only player you have to consider, but also panel you need to do too.
         * 
         * Hint       pseudo code provide here
         *            doNothing()
         *            if axis = 1 -> move in y else x    
         *            panel.setCenter( new Center + player.getPerStep()) # Note the direction
         *            panel.repaint()、player.repaint()
         * 
         * 
         **********************************The End of the TODO***************************************/
        /********************************************************************************************
         START OF YOUR CODE
         ********************************************************************************************/
        doNothing(milliseconds);
         if(axis==0){          
                 int CenterX=panel.getCenterX();
                 if(direction<0)
                     panel.setCenterX(CenterX-player.getPerStep());
                 else
                     panel.setCenterX(CenterX+player.getPerStep());
                 //player.setMapX(playerX+(direction/8));
                 panel.repaint();
                 player.repaint();
             
         }
         else{
                 int CenterY=panel.getCenterY();
                 if(direction<0)
                     panel.setCenterY(CenterY-player.getPerStep());
                 else
                     panel.setCenterY(CenterY+player.getPerStep());
                 //player.setMapY(playerY+(direction/player.getStepTime()));
                 panel.repaint();
                 player.repaint();
             
         }
         /********************************************************************************************
         END OF YOUR CODE
         ********************************************************************************************/
    }
    //Description : Stop the thread in milliseconds.
    private static void doNothing(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption");
            System.exit(0);
        }
    }
    



}

