import edu.gwu.util.*;
import edu.gwu.debug.*;
import edu.gwu.algtest.*;
import java.lang.*;
import java.util.*;

public class MyRLTree implements TreeSearchAlgorithm 
{
	
    public TreeNode root = null;
    public int count = 0;
    public int maxSize = Integer.MAX_VALUE;

    public java.lang.Object insert(java.lang.Comparable key, java.lang.Object value)
    {
	if (count >= maxSize) 
	{
	    return null;
	}

	edu.gwu.algtest.TreeNode n = getRoot();
	if (root == null) 
	{
	    root = new edu.gwu.algtest.TreeNode (key, value);
	    root.numericLabel = UniformRandom.uniform();
	    return value;
	}

	while (n != null) 
	{
	    if (key.compareTo(n.key) == 0) 
	    {
		n.value = value;
		return value;
	    }
	    else if (key.compareTo(n.key) < 0) 
	    {		
		if (n.left == null) 
		{
		    n.numericLabel = UniformRandom.uniform();
		    n.left = new edu.gwu.algtest.TreeNode (key, value, null, null, n);
		    n = n.left;
		    count++;
		    return value;
		}
		else 
		{
		    n = n.left;
		}
	    }			
	    else 
	    {	
		if (n.right == null) 
		{
		    n.numericLabel = UniformRandom.uniform();
		    n.right = new edu.gwu.algtest.TreeNode (key, value, null, null, n);
		    n = n.right;
		    count++;
		    return value;
		}

		else 
		{
		    n = n.right;
		}
	    }
	}		
	int status = 0;
	while (n.numericLabel < n.parent.numericLabel) 
	{
	    if (n.parent == root) 
	    { 
		status = 1;
	    }
	    else if (isLeftChild(n) && isLeftChild(n))
	    {
	       	status = 2;
	    }
	    else if (isRightChild(n) && isRightChild(n))
	    {
		status = 3;
	    }
	    else if (isRightChild(n) && isLeftChild(n))
	    {
	       	status = 4;
	    }
	    else if (isLeftChild(n) && isRightChild(n.parent))
	    {
	       	status = 5;
	    }

	    switch (status) 
	    {
		case 1:
			if (isLeftChild(n)) 
			{
	 		    rotateRight(n);
			} 
			else 
			{
			    rotateLeft(n);
			}
			break;
		case 2:
			rotateRight(n.parent);
			rotateRight(n);
			break;
		case 3: 
			rotateLeft(n.parent);
			rotateLeft(n);
			break;
		case 4:
			rotateLeft(n);
			rotateRight(n);
			break;
		case 5:
			rotateRight(n);
			rotateLeft(n);
			break;
		default:
			break;
	    }

	}

	return value;
    }

    public boolean isLeaf(TreeNode n) 
    {
	if (n.left == null && n.right == null)
	    return true;
	return false;
    }

    public boolean isLeftChild(edu.gwu.algtest.TreeNode n) 
    {
	if (n.parent == null)
	    return false;
	else if (n.parent.left == n) 
	    return true;
	return false;
    }

    public boolean isRightChild(edu.gwu.algtest.TreeNode n) 
    {
	if (n.parent == null)
	    return false;
	else if (n.parent.right == n) 
	    return true;
	return false;
    }

    public boolean hasLeft(edu.gwu.algtest.TreeNode n) 
    {
	if (n.left != null)
	    return true;
	return false;
    }
	
    public boolean hasRight(edu.gwu.algtest.TreeNode n) 
    {
	if (n.right != null)
	    return true;
	return false;
    }


    public void rotateRight(edu.gwu.algtest.TreeNode n) 
    {
	edu.gwu.algtest.TreeNode temp = null;
	edu.gwu.algtest.TreeNode newNode = null;
	edu.gwu.algtest.TreeNode parentNode = n.parent;
	if (hasRight(n)) 
	{  
	    parentNode.left = n.right;
	    n.right.parent = parentNode;
	} 
	else 
	{
	    parentNode.left = null;
	}

	if (parentNode.parent != null) 
	{
	    newNode = parentNode.parent;
	    n.parent = parentNode.parent;
	    if (isLeftChild(parentNode))
		newNode.left = n;
	    else 
	        newNode.right = n;
	} 
	else 
	{
	    root = n;
	    n.parent = null;
	}

	n.right = parentNode;
	parentNode.parent = n;

	return;
    }


    public void rotateLeft(edu.gwu.algtest.TreeNode n) 
    {
	edu.gwu.algtest.TreeNode temp = null;
	edu.gwu.algtest.TreeNode newNode = null;
	edu.gwu.algtest.TreeNode parentNode = n.parent;
	if (hasLeft(n)) 
	{  
	    parentNode.right = n.left;
	    n.left.parent = parentNode;
	} 
	else 
	{
	    parentNode.right = null;
	}

	if (parentNode.parent != null) 
	{
	    newNode = parentNode.parent;
	    n.parent = parentNode.parent;
	    if (isLeftChild(parentNode))
		newNode.left = n;
	    else 
		newNode.right = n;
	} 
	else 
	{
	    root = n;
	    n.parent = null;
	}

	n.left = parentNode;
	parentNode.parent = n;

	return;
    }
	

    public edu.gwu.algtest.ComparableKeyValuePair search(java.lang.Comparable key)
    {
	return recursiveSearch(root,key);
    }

    public edu.gwu.algtest.ComparableKeyValuePair recursiveSearch (edu.gwu.algtest.TreeNode n, java.lang.Comparable key)
    {	   
	if (root == null)
	{
	   return null;
	}
	
	if (n.key.compareTo(key) == 0)
	{
		
	   return n;
	}
	
	if (key.compareTo(n.key) < 0) 
	{
           if (n.left == null) 
	   {  	   		
		return null;
	   }
	   else 
	   {
		return recursiveSearch(n.left, key);
	   }
	}
	else 
	{
	   if (n.right == null)
	   {  	   		
		return null;
	   }
	   else 
	   {	
		return recursiveSearch(n.right, key);
	   }
	}

    }

    public edu.gwu.algtest.ComparableKeyValuePair minimum() 
    {
		
	edu.gwu.algtest.TreeNode node = getRoot();
	while(node.left != null) 
	{
	    node = node.left;
	}
	return node;
    }

    public edu.gwu.algtest.ComparableKeyValuePair maximum() 
    {
	edu.gwu.algtest.TreeNode node = getRoot();
	while(node.right != null) 
	{
	    node = node.right;
	}
	return node;
    }

    public java.lang.Object delete(Comparable key) 
    {
	return null;
    }

    public java.lang.Comparable successor(java.lang.Comparable key) 
    { 
	return null;
    }

    public java.lang.Comparable predecessor(java.lang.Comparable key) 
    { 
	return null;	
    }

    public edu.gwu.algtest.TreeNode getRoot() 
    {
	return root;
    }	
	
    public java.lang.String getName() 
    { 
	return "RLTree";
    }

    public void setPropertyExtractor(int algID, PropertyExtractor prop) 
    {
	return;
    }

    public void initialize(int maxSize) 
    {
	this.maxSize = maxSize;
	count = 0;
	root = null;
	return;
    }

    public int getCurrentSize()
    { 
	return count; 
    }

    public java.util.Enumeration getKeys() 
    { 	
	class TreeKeys implements Enumeration 
	{
	    edu.gwu.algtest.TreeNode node = (edu.gwu.algtest.TreeNode) minimum();
	    int index = 0;
	    public boolean hasMoreElements() 
	    {		
		edu.gwu.algtest.TreeNode max = (edu.gwu.algtest.TreeNode) maximum();
		if (node == max) 
		{			
		    return false;
		}		
		return true;
	    }
	    public Object nextElement() 
	    {		
		if (!hasMoreElements()) 
		{
		    return null;
		}
				
		if (index == 0) 
		{
	  	    index++;
		    return node.value;
		}
				
		if (node.left != null) 
		{
		    return node.left.key;
		}
		if (node.right != null) 
		{
		    return node.right.key;
		}
		return node.key;
	     }

	    public java.util.Enumeration getEnumeration() 
	    { 
		node = (edu.gwu.algtest.TreeNode) minimum();
		index = 0;
		return this; 
	    }

	}
	return new TreeKeys();
    }

    public java.util.Enumeration getValues() 
    {
	class TreeValues implements Enumeration 
	{		
	    edu.gwu.algtest.TreeNode node = (edu.gwu.algtest.TreeNode) minimum();
	    int index = 0;
	    public boolean hasMoreElements() 
	    {
		edu.gwu.algtest.TreeNode max = (edu.gwu.algtest.TreeNode) maximum();
		if (node == max) 
		{		
		    return false;
		}		
		return true;
	    }
	    public Object nextElement() 
	    {	
		if (!hasMoreElements()) 
		{
		    return null;
		}
				
		if (index == 0) 
		{
		    index++;
		    return node.value;
		}
		if (node.left != null) 
		{
		    return node.left.value;
		}
		if (node.right != null) 
		{
		    return node.right.value;
		}
		return node.value;
	    }

	    public java.util.Enumeration getEnumeration() 
	    { 
		node = (edu.gwu.algtest.TreeNode) minimum();
		return this; 
	    }

	}
	return new TreeValues();
    }

    public void initialize() 
    {
	root = null;
	count = 0;
	return;
    }

    public static void main(String[] args) 
    {

	MyRLTree bst = new MyRLTree();
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
	System.out.println(bst.search(10));
	System.out.println(bst.search(4));
	System.out.println(bst.search(20));



	System.out.println();
    }
}
