import java.lang.*;
import java.util.*;
import edu.gwu.algtest.*;                   // These three import's are needed.
import edu.gwu.debug.*;
import edu.gwu.util.*;


public class MyBinarySearchTree implements TreeSearchAlgorithm
{
   public edu.gwu.algtest.TreeNode root;
   int count = 0;

   public edu.gwu.algtest.TreeNode getRoot()
   {
	return root;
   }
   public java.lang.Object delete(java.lang.Comparable key)
   {

	java.lang.Object value = new java.lang.Object(); 

	edu.gwu.algtest.ComparableKeyValuePair sKVP = new edu.gwu.algtest.ComparableKeyValuePair();
	sKVP = search(key);	
	edu.gwu.algtest.TreeNode node =  new edu.gwu.algtest.TreeNode();
	if (sKVP != null)
	{
	node.key = sKVP.key;
	node.value = sKVP.value;
	value = node.value;


	}
	if (node == null) 
	{
		return null;
	}


	edu.gwu.algtest.TreeNode n = node;


	int status = 0;


	if (hasLeft(node))
	{ 

		status = 1;
	}
	else if (hasRight(node))
	{ 


		status = 2;
	}
	else if (isLeaf(node))
	{ 

		status = 3;
	}


	switch (status) {

		case 1:
			// Get predecessor
			n = n.left;
			while (hasRight(n)) {
				n = n.right;


			}

			if (isLeftChild(n)) 
			{

				if (isLeaf(n)) {
					n.parent.left = null;
				}
					else {

						if (hasRight(n.parent)) {
							n.right = n.parent.right;
							n.right.parent = n;
						}

						if (n.parent.parent != null) {
							n.parent = n.parent.parent;
							n.parent.left = n;



						} else {
							n.parent = null;
							root = n;
						}
					}

			} 
			else if (hasLeft(n)) {

					n.left.parent = n.parent;
					n.parent.right = n.left;

			}
			else {
					n.parent.right = null;
				}

				node.key = n.key;


				node.value = n.value;


				break;

			case 2:
				n = n.right;
				while (hasLeft(n)) 
					n = n.left;

				if (isRightChild(n)) {

					if (isLeaf(n)) 
						n.parent.right = null;
					else {

						if (hasLeft(n.parent)) {
							n.left = n.parent.left;
							n.left.parent = n;
						}

						if (n.parent.parent != null) {
							n.parent = n.parent.parent;
							n.parent.right = n;
						// Otherwise set root to node
						} else {
							n.parent = null;
							root = n;
						}
					}

				} else if (hasRight(n)) {

					n.right.parent = n.parent;
					n.parent.right = n.right;

				} else {
					n.parent.left = null;
				}

				node.key = n.key;
	System.out.println("thahstts");

				node.value = n.value;
	System.out.println("errrrrrrayaaaaa");

				break;

			case 3:


				if (isLeftChild(n))
				{ 
					
					n.parent.left = null;
				}
				else if (isRightChild(n))
				{ 
			
					n.parent.right = null;
				}

				else 
					root = null;
				break;
			default:
				break;
		}
		return value;
   }
   
   public java.lang.Object insert(java.lang.Comparable key, java.lang.Object value)
   {
	//System.out.println("insert "+key.toString());
	//System.out.println("insert "+value.toString());

	return recursiveInsert(root,key,value);
   }
   public java.lang.Object recursiveInsert(edu.gwu.algtest.TreeNode n, java.lang.Comparable key, java.lang.Object value)
   {
	//System.out.println("recursive is called ");

	//System.out.println("insert key = " + key.toString());
	if (getRoot() == null) 
	{
            root = new edu.gwu.algtest.TreeNode();
	    root.key = key;
            root.value = value;
	    //System.out.println("recursive is called ");
            return null;
        }
	
	//if (contains(key)) 
	//{
            //Still need to Handle duplicates.
		//System.out.println("lets go");
          //  java.lang.Object temp = n.value;
		//System.out.println("we here");
	    //n.key = key;
	    //n.value = value;
	    //return temp;
        //}


	if (key.compareTo(n.key) < 0)
	{
	   if (n.left != null)
	   {
		recursiveInsert(n.left, key, value);
	   }
	   else
	   {
		n.left = new edu.gwu.algtest.TreeNode();
		n.left.key = key;
		n.left.value = value;	
		//return null;
	
	   }
	}
	else 
	{
	   if (n.right != null) 
	   {
		recursiveInsert (n.right, key, value);
           }
           else {
                n.right = new edu.gwu.algtest.TreeNode();
                n.right.key = key;
		n.right.value = value;
		//return null;

            }
        }
	//System.out.print(key);
	return null;

   }
   
   

   public boolean contains(java.lang.Comparable key)
   {
	//System.out.println("ganggg");	   	   		

	if (search(key) != null)
	{
	    return true;
	}
	else 
	{	
	    return false;
	}
   }

   public edu.gwu.algtest.ComparableKeyValuePair maximum()
   {
	edu.gwu.algtest.TreeNode node = new edu.gwu.algtest.TreeNode();
	edu.gwu.algtest.TreeNode maxNode = new edu.gwu.algtest.TreeNode();
	node = getRoot();
	maxNode = getRoot();

	java.lang.Comparable mink;
	mink = root.key;

	while (node.right != null) 
	{
	    node = node.right;
 	    if (node.key.compareTo(mink) > 0)
	    {
		mink = node.key;
		maxNode = node;
	    }
	//System.out.print(node.key);
	//node = node.left;
	}
	edu.gwu.algtest.ComparableKeyValuePair kvp = new edu.gwu.algtest.ComparableKeyValuePair();
	kvp.key = maxNode.key;
	kvp.value = maxNode.value;
	//System.out.println("maxNode = " + maxNode);
	
	return maxNode;
   }	

   public edu.gwu.algtest.ComparableKeyValuePair minimum()
   {
	edu.gwu.algtest.TreeNode node = new edu.gwu.algtest.TreeNode();
	edu.gwu.algtest.TreeNode minnode = new edu.gwu.algtest.TreeNode();
	node = getRoot();
	minnode = root;
	//System.out.println("minFirstNode = " + minnode);
	
	java.lang.Comparable mink;
	mink = root.key;

	while (node.left != null) 
	{
	    node = node.left;
 	    if (node.key.compareTo(mink) < 0)
	    {
		//System.out.println("Im in here yesss");
		mink = node.key;
		minnode = node;
		//System.out.println("Is the error in here?");

	    }
		//System.out.println("What about here?");
	
	//System.out.print(node.key);
	//node = node.left;
	}
	//System.out.println("minNode = " + minnode);
	//System.out.println("minNode Right= " + minnode.right);
	//System.out.println("minNode Right Right= " + minnode.right.right);
	//System.out.println("minNode Right Right Right= " + minnode.right.right.right);
	//System.out.println("minNode Left= " + minnode.left);

	   edu.gwu.algtest.ComparableKeyValuePair kvp = new edu.gwu.algtest.ComparableKeyValuePair();
	   kvp.key = minnode.key;
	   kvp.value = minnode.value;

	return minnode;
   }	    
   
   public java.lang.Comparable predecessor(java.lang.Comparable key)
   {
	edu.gwu.algtest.TreeNode node =  new edu.gwu.algtest.TreeNode();
	edu.gwu.algtest.ComparableKeyValuePair KVP = new edu.gwu.algtest.ComparableKeyValuePair();
	KVP = search(key);
	node.key = KVP.key;
	node.value = KVP.value;

	edu.gwu.algtest.TreeNode min =  new edu.gwu.algtest.TreeNode();
	edu.gwu.algtest.ComparableKeyValuePair minKVP = new edu.gwu.algtest.ComparableKeyValuePair();
	KVP = minimum();
	min.key = minKVP.key;
	min.value = minKVP.value;

	// If current node is the minimum node, return null
	if (key.compareTo(min.key) == 0) 
	{
		return null;
	}
	// If current node has a left subtree, return the maximum 
	// of that subtree
	if (hasLeft(node)) 
	{
		node = node.left;
		while(hasRight(node)) 
			node = node.right;
	// If current node is a right child, return it's parent node
	}
	else if (isRightChild(node)) 
	{
		node = node.parent;
	// Otherwise, navigate up the tree until the active node is a right
	// child, then return its parent
	}
	else 
	{
		while(!isRightChild(node)) 
			node = node.parent;
		node = node.parent;
	}
	return node.key;

   }

   public edu.gwu.algtest.ComparableKeyValuePair search(java.lang.Comparable key)
   {
	//System.out.println("search for" + key.toString());
	return recursiveSearch(root,key);   	   
   }

   public edu.gwu.algtest.ComparableKeyValuePair recursiveSearch (edu.gwu.algtest.TreeNode n, java.lang.Comparable key)
   {
	//System.out.println("heyyyyy");
	
	   //System.out.println("search key = " + key.toString());	   	   
	//System.out.println("search for" + key.toString());
 	   

	if (root == null)
	{
	//System.out.println("search for" + key.toString());

	   return null;
	}
	//System.out.println("searching for" + key.toString());
	
	if (n.key.compareTo(key) == 0)
	{
	   //System.out.println( "search key is " + n.key.toString() );	   	   
	   //edu.gwu.algtest.ComparableKeyValuePair kvp = new edu.gwu.algtest.ComparableKeyValuePair();
	   //kvp.key = n.key;
	   //kvp.value = n.value;
	   //System.out.println( "search value is " + n.value.toString() );
	   //System.out.println( "search kvp is " + kvp );
		
	   return n;
	}
	
	if (key.compareTo(n.key) < 0) 
	{
           //Go left if possible, otherwise it's not in the tree.
           if (n.left == null) 
	   {
		//System.out.println("did we enter");	   	   		
		return null;
	   }
	   else 
	   {
		//System.out.println("yessirr");
		return recursiveSearch(n.left, key);
	   }
	}
	else 
	{
	   if (n.right == null)
	   {
		//System.out.println("yes i made it");	   	   		
		return null;
	   }
	   else 
	   {
		//System.out.println("nooo sirr");		
		return recursiveSearch(n.right, key);
	   }
	}

   }

	public java.lang.Comparable successor(java.lang.Comparable key){
		
		TreeNode node = (TreeNode) search(key);
		TreeNode max = (TreeNode) maximum();

		// If current node is the maximum node, return null
		if (key.compareTo(max.key) == 0) {
 			return null;
		}

		// If current node has a right subtree, return the minimum 
		// of that subtree
		if (hasRight(node)) {
			node = node.right;
			while(hasLeft(node)) 
				node = node.left;
		// If current node is a left child, return it's parent node
		} else if (isLeftChild(node)) {
			node = node.parent;
		// Otherwise, navigate up the tree until the active node is a right
		// child, then return its parent
		} else {
			while(!isLeftChild(node)) 
				node = node.parent;
			node = node.parent;
		}
		return node.key;
	}
   public void initialize(int maxSize)
   {
	root = null;	
   }

   public int getCurrentSize()
   {
	return count;
   }


   public java.lang.String getName()
   {
	return " My Binary Search Tree";
   }
	public java.util.Enumeration getKeys(){
		
		class TreeKeys implements Enumeration {
			// Set active node to minimum node
			TreeNode node = (TreeNode) minimum();
			// Keep track of index
			int index = 0;

			public boolean hasMoreElements() {
				// Get maximum node, and check if active node is equal to maximum node
				TreeNode max = (TreeNode) maximum();
				if (node == max) {
					// If they are equal, there are no more elements. Return false.
					return false;
				}
				// Otherwise, there are. Return true.
				return true;
			}

			public Object nextElement() {
				// Check if tree has a next element
				if (!hasMoreElements()) {
					return null;
				}
				// If index is 0, return first element as "next" element
				if (index == 0) {
					index++;
					return node.value;
				}
				// Get successor key, and get succeding node
				Comparable key = successor(node.key);
				node = (TreeNode) search(key);
				index++;
				return node.key;
			}

			public Enumeration getEnumeration() { 
				// Reset enumeration and return this
				node = (TreeNode) minimum();
				index = 0;
				return this; 
			}

		}
		return new TreeKeys();
	}

	public java.util.Enumeration getValues(){

		class TreeValues implements Enumeration {
			// Set active node to minimum node
			TreeNode node = (TreeNode) minimum();
			// Keep track of index
			int index = 0;

			public boolean hasMoreElements() {
				// Get maximum node, and check if active node is equal to maximum node
				TreeNode max = (TreeNode) maximum();
				if (node == max) {
					// If they are equal, there are no more elements. Return false.
					return false;
				}
				// Otherwise, there are. Return true.
				return true;
			}

			public Object nextElement() {
				// Check if tree has a next element
				if (!hasMoreElements()) {
					return null;
				}
				// If index is 0, return first element as "next" element
				if (index == 0) {
					index++;
					return node.value;
				}
				// Get successor key, and get succeding node
				Comparable key = successor(node.key);
				node = (TreeNode) search(key);
				index++;
				return node.value;
			}

			public Enumeration getEnumeration() { 
				// Reset enumeration and return this
				node = (TreeNode) minimum();
				return this; 
			}

		}
		return new TreeValues();
   }
   public void setPropertyExtractor(int algID, edu.gwu.util.PropertyExtractor prop)   
   {
	return;
   }	
	public boolean isLeaf(edu.gwu.algtest.TreeNode node) {
		if (node.left == null && node.right == null)
			return true;
		return false;
	}

	public boolean isLeftChild(edu.gwu.algtest.TreeNode node) {
		if (node.parent == null)
			return false;
		else if (node.parent.left == node) 
			return true;
		return false;
	}

	public boolean isRightChild(edu.gwu.algtest.TreeNode node) {
		if (node.parent == null)
			return false;
		else if (node.parent.right == node) 
			return true;
		return false;
	}

	public boolean hasLeft(edu.gwu.algtest.TreeNode node) {
		if (node.left != null)
			return true;
		return false;
	}
	
	public boolean hasRight(edu.gwu.algtest.TreeNode node) {
		if (node.right != null)
			return true;
		return false;
	}

   public static void main (String[] argv)
   {
	MyBinarySearchTree bst = new MyBinarySearchTree();
	System.out.println("Inserting");
	bst.insert(10, "hi");
	System.out.println(1);
	bst.insert(20, "hello");
	System.out.println(2);
	bst.insert(50, "good morning");
	System.out.println(3);
	bst.insert(40, "greetings");
	System.out.println(4);
	bst.insert(4, "hey");
	bst.insert(5, "groovy");
	System.out.println(bst.search(5));
	System.out.println(bst.search(15));
	System.out.println(bst);

   }

} 
