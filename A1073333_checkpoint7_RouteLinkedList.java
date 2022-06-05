public class A1073333_checkpoint7_RouteLinkedList{
    private A1073333_checkpoint7_Node head;
    //Description : the constructor of leading the head Node as null.
    public A1073333_checkpoint7_RouteLinkedList(){
        this.head = null;
    }
    //Description : the constructor of input a Node as the head node.
    public A1073333_checkpoint7_RouteLinkedList(A1073333_checkpoint7_Node head){
        this.head = head;
    }
    public void delete(int axis, int direction){ 
        /*********************************The Past TODO (Checkpoint5)******************************
        //TODO(1):      Input value of Node as the reference Node,
        //              you have to delete the first Node that is same as the reference Node,
        //              and connect the following one and the previous one.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1073333_checkpoint7_Node prev = null;
        A1073333_checkpoint7_Node current = head;
        if(head==null)System.out.println("The list is empty");
        else{
            if(head.getNext()==null){
                head=null;
            }else{
                while((current.getDirection()!=direction&&current.getAxis()!=axis)&&current.getNext()!=null){
                    prev=current;
                    current=current.getNext();
                }
                if(current.getDirection()==direction&&current.getAxis()==axis){
                    if(prev==null)head=current.getNext();
                    else prev.setNext(current.getNext());
                }
                else{
                    // System.out.println("Data is not in the list!");
                }
            }
        }
        
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }

    public A1073333_checkpoint7_Node search(int axis, int direction){ 
        /*********************************The Past TODO (Checkpoint5)********************************
        //TODO(2):      Input value of Node as the reference Node,
        //              you have to find the first Node that is same as the reference Node,
        //              and return it.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1073333_checkpoint7_Node prev = null;
        A1073333_checkpoint7_Node current = head;
        if(head==null){
            System.out.println("The list is empty");
            return null;
        }
        else{
            while((current.getDirection()!=direction&&current.getAxis()!=axis)&&current.getNext()!=null){
                prev=current;
                current=current.getNext();
            }
            if(current.getDirection()==direction&&current.getAxis()==axis){
                return current;
            }
            else{
                // System.out.println("Data is not in the list!");
                return null;
            } 
        }
        
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }
    public void insert(int referenceAxis, int referenceDirection, int axis, int direction){ 
        /*********************************The Past TODO (Checkpoint6)********************************
        //TODO(3):      Input value of Node as the reference Node,
        //              and insert a Node BEFORE the first Node same as the reference Node,
        //              and connect the following one and the previous one.
        //Hint          The value of the Node is int variable axis and dirsction.
        //Hint2         If there is no reference node in linkedlist, print "Insertion null".
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1073333_checkpoint7_Node node= new A1073333_checkpoint7_Node(direction, axis);
        A1073333_checkpoint7_Node prev = null;
        A1073333_checkpoint7_Node current = head;
        if(head==null){
            head = node;
        }
        else{
            while(current.getDirection()!=referenceDirection||current.getAxis()!=referenceAxis&&current.getNext()!=null){
                // System.out.println("change");
                prev=current;
                current=current.getNext();
            }
            // if(prev==null){
            //     node.setNext(current);
            //     head=node;
            //     // System.out.println("hi");
            // }
            if(current.getDirection()==referenceDirection&&current.getAxis()==referenceAxis){
                // System.out.println(current.getAxis()+" "+current.getDirection());
                node.setNext(current);
                if(prev==null)head=node;
                else prev.setNext(node);
            }
             
            else{System.out.println("Insertion null");}
        }


        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }
    public int length(){
        /*********************************The Past TODO (Checkpoint5)********************************
        //TODO(4):      return how long the LinkedList is.
        /********************************************************************************************
         START OF YOUR CODE
        ********************************************************************************************/
        A1073333_checkpoint7_Node current = head;
        int size = 1;
        if(head==null)return 0;
        else{
            while(current.getNext()!=null){
                size++;
                current=current.getNext();
            }
            return size;
        }
        
        /********************************************************************************************
         END OF YOUR CODE
        ********************************************************************************************/
    }
    public void append(int axis, int direction){
        A1073333_checkpoint7_Node node = this.head;
        while(node.getNext() != null){
            node = node.getNext();
        }
        node.setNext(new A1073333_checkpoint7_Node(direction, axis));
    }
    public A1073333_checkpoint7_Node getHead(){
        return this.head;
    }
    public void setHead(A1073333_checkpoint7_Node head){
        this.head = head;
    }
}
    

