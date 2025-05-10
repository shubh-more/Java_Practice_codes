package algs13;

public class MyLinked {
	    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinked () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    // return Double.NEGATIVE_INFINITY if the linked list is empty
    public double max () { return max (first); }
    private static double max (Node x) {
        // TODO 1.3.27
    	Node temporary=x;
    	double maximum= Double.NEGATIVE_INFINITY;
    	while(temporary!=null)
    	{
    		if(temporary.item>maximum)
    		{
    			maximum =temporary.item;
    		}
    	temporary=temporary.next;
    	}
        return maximum;
    }

    public double maxRecursive () { return maxRecursive (first, Double.NEGATIVE_INFINITY); }
    private static double maxRecursive (Node x, double result) {
        if(x==null)
        {
        	return Double.NEGATIVE_INFINITY;
        }
        if(x.next==null)
        {
        	 return x.item;
        }
        double temporary=maxRecursive(x.next,result);
        if(x.item>temporary)
        {
        	temporary=x.item;
        }
        result =temporary;
        return result;
        
    }
     
    // delete the kth element
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
       if(k==0)
       {
    	   this.first=this.first.next;
       }
       else
       {
    	   Node temporary =this.first;
    	   for(int i =0;i<(k-1);i++) {
    		   temporary =temporary.next;
    		   
    	   }
    	   temporary.next=temporary.next.next;
       }
       this.N-=1;
        checkInvariants ();
    }

    // reverse the list "in place"... without creating any new nodes
    public void reverse () {
      if(first==null) {
    	  return;
      }
      Node previous=null,temporary=first,nxt=temporary.next;
      while(temporary!=null)
      {nxt=temporary.next;
      temporary.next=previous;
      previous =temporary;
      temporary=nxt;
      }
      first =previous;
        checkInvariants ();
    }

    // remove 
    public void remove (double item) {
     if(first==null)
     {
    	 return;
     }
     if(first.item==item)
     {
    	 this.first =this.first.next;
    	 this.N-=1;
     }
     else 
     {
    	 Node temporary =this.first,prev=this.first;
    	 int flag=0;
    	 while(temporary!=null) {
    		 if(temporary.item==item)
    		 {
    			 flag=1;
    			 break;
    		 }
    	prev = temporary;
    	temporary=temporary.next;
    	
    	 }
    	 if(flag==1)
    	 {
    		 prev.next=temporary.next;
    	 this.N-=1;
    	 }
    	 
     }
        checkInvariants ();
    }


    private static void print (String s, MyLinked b) {
        System.out.print (s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            System.out.print (x.item + " ");
        System.out.println ();
    }
    private static void print (String s, MyLinked b, double i) {
        System.out.print (s + ": ");
        for (Node x = b.first; x != null; x = x.next)
            System.out.print (x.item + " ");
        System.out.println (": " + i);
    }

    private static void testMax () {
        MyLinked b = new MyLinked ();
        print ("empty", b, b.max());
        b.add (-1);
        print ("singleton", b, b.max());
        b.add (-2);
        b.add (-3);
        b.add (-4);
        print ("at end", b, b.max());
        b.add (5);
        print ("at beginning", b, b.max());
        b.add (3);
        b.add (2);
        b.add (4);
        print ("in the middle", b, b.max());
    }
    private static void testMaxRecursive () {
        MyLinked b = new MyLinked ();
        print ("empty", b, b.maxRecursive());
        b.add (-1);
        print ("singleton", b, b.maxRecursive());
        b.add (-2);
        b.add (-3);
        b.add (-4);
        print ("at end", b, b.maxRecursive());
        b.add (5);
        print ("at beginning", b, b.maxRecursive());
        b.add (3);
        b.add (2);
        b.add (4);
        print ("in the middle", b, b.maxRecursive());
    }
    private static void testDelete () {
        MyLinked b = new MyLinked ();
        b.add (1);
        print ("singleton", b);
        b.delete (0);
        print ("deleted", b);
        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        print ("bigger list", b);
        b.delete (0);
        print ("deleted at beginning", b);
        b.delete (10);
        print ("deleted at end", b);
        b.delete (4);
        print ("deleted in middle", b);
    }
    private static void testReverse () {
        MyLinked b = new MyLinked ();
        b.reverse ();
        print ("reverse empty", b);
        b.add (1);
        print ("singleton", b);
        b.reverse ();
        print ("reverse singleton", b);
        b.add (2);
        print ("two", b);
        b.reverse ();
        print ("reverse two", b);
        b.reverse ();
        print ("reverse again", b);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        print ("bigger list", b);
        b.reverse ();
        print ("reversed", b);
    }
    private static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        print ("removed 4 from empty", b);
        b.add (1);
        b.remove (4);
        print ("removed 4 from singelton", b);
        b.remove (1);
        print ("removed 1 from singelton", b);
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
        }
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
        }
        print ("longer list", b);
        b.remove (9);
        print ("removed all 9s", b); // does nothing
        b.remove (3);
        print ("removed all 3s", b);
        b.remove (1);
        print ("removed all 1s", b);
        b.remove (4);
        print ("removed all 4s", b);
        b.remove (2);
        print ("removed all 2s", b); // should be empty
    }

    public static void main (String args[]) {
              testMax ();
              testMaxRecursive ();
              testDelete ();
              testReverse ();
              testRemove ();
    }
}



