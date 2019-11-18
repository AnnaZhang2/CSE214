/**
 * A class that represents a node in the file tree, containing 3 DirectoryNode
 * references (left, middle, right), a String member variable name, which
 * indicates the name of the node in the tree.
 *
 *  @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  Section: 06
 *  Stony Brook ID: 112167606
 */

public class DirectoryNode
{
    private DirectoryNode left, middle, right, parent;
    private String name;
    private boolean isFile;

    public DirectoryNode()
    {
        name=null;
        parent=null;
        left=null;
        middle=null;
        right=null;
    }
    /**
     * Creates a DirectoryNode object with a given name
     *
     * @param n
     *  A String object for the name of the node in the tree
     */
    public DirectoryNode(String name, boolean isFile)
    {
        if(name.indexOf(" ")!=-1||name.indexOf("/")!=-1)
            throw new IllegalArgumentException("The name of the directory "
                    + "should not contain spaces or forward slashes.");
        this.name=name;
        parent=null;
        left=null;
        middle=null;
        right=null;
        this.isFile=isFile;
    }

    //Accessor methods
    /**
     * Returns the name of the DirectoryNode
     *
     * @return
     *  String that contains the name of the DirectoryNode
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the parent of the DirectoryNode.
     *
     * @return
     *  The parent of the DirectoryNode
     */
    public DirectoryNode getParent()
    {
        return parent;
    }

    /**
     * Returns the left child of the DirectoryNode
     *
     * @return
     *  The left child of the DirectoryNode
     */
    public DirectoryNode getLeft()
    {
        return left;
    }

    /**
     * Returns the middle child of the DirectoryNode
     *
     * @return
     *  The middle child of the DirectoryNode
     */
    public DirectoryNode getMiddle()
    {
        return middle;
    }

    /**
     * Returns the right child of the DirectoryNode
     *
     * @return
     *  The right child of the DirectoryNode
     */
    public DirectoryNode getRight()
    {
        return right;
    }

    /**
     * Returns whether or not the node is a file.
     *
     * @return
     *  True if the node is a file. False otherwise.
     */
    public boolean getIsFile()
    {
        return isFile;
    }

    //mutator methods
    /**
     * Replaces the current name of the DirectoryNode with the given name
     *
     * @param n
     *  given name of the DirectoryNode
     */
    public void setName(String name)
    {
        if(name.indexOf(" ")!=-1||name.indexOf("/")!=-1)
            throw new IllegalArgumentException("The name of the directory should"
                    + "not contain spaces or forward slashes.");
        this.name=name;
    }

    /**
     * Updates the parent of the current DirectoryNode
     *
     * @param parent
     *  The given parent node of a DirectoryNode
     */
    public void setParent(DirectoryNode parent)
    {
        this.parent=parent;
    }

    /**
     * Replaces the left node with a given node
     *
     * @param left
     *  A node reference
     */
    public void setLeft(DirectoryNode left)
    {
        this.left=left;
    }

    /**
     * Replaces the middle node with a given node
     *
     * @param middle
     *  A node reference
     */
    public void setMiddle(DirectoryNode middle)
    {
        this.middle=middle;
    }

    /**
     * Replaces the right node with a given node
     *
     * @param right
     *  A node reference
     */
    public void setRight(DirectoryNode right)
    {
        this.right=right;
    }

    /**
     * Adds newChild to any of the open child positions of this node (left,
     * middle, or right)
     *
     * Preconditions:
     *  This node is not a file.
     *  There is at least one empty position in the children of this node (left,
     *  middle, or right).
     *
     * Postconditions:
     *  newChild has been added as a child of this node. If there is no room for
     *  a new node, throw a FullDirectoryException.
     *
     * @param newChild
     *  A given DirectoryNode to add to any open positions of this node
     *
     * @throws FullDirectoryException: Thrown if the current node is a file, as
     *  files cannot contain DirectoryNode references (i.e. all files are
     *  eaves)
     *
     * @throws NotADirectoryException: Thrown if all child references of this
     *  directory are occupied
     */
    public void addChild(DirectoryNode newChild) throws FullDirectoryException,
            NotADirectoryException, SameNameException
    {
        //not a directory
        if(newChild.getIsFile())
            throw new NotADirectoryException();
        if(left==null)
        {
            left=newChild;
            left.setParent(this);
        }
        else if(middle==null)
        {
            if(left.getName().equals(newChild.getName()))
                throw new SameNameException();
            middle=newChild;
            middle.setParent(this);
        }
        else if(right==null)
        {
            if(middle.getName().equals(newChild.getName()) ||
                    left.getName().equals(newChild.getName()))
                throw new SameNameException();
            right=newChild;
            right.setParent(this);
        }
        else
            throw new FullDirectoryException();
    }

    /**
     * Adds newChild to any of the open child positions of this node (left,
     * middle, or right)
     *
     * Preconditions:
     *  There is at least one empty position in the children of this node (left,
     *  middle, or right).
     *
     * Postconditions:
     *  newChild has been added as a child of this node. If there is no room for
     *  a new node, throw a FullDirectoryException.
     *
     * @param newChild
     *  A given DirectoryNode to add to any open positions of this node
     *
     * @throws FullDirectoryException: Thrown if the current node is a file, as
     *  files cannot contain DirectoryNode references.
     */
    public void addFile(DirectoryNode newChild) throws FullDirectoryException,
            SameNameException
    {
        if(left==null)
        {
            left=newChild;
            left.setParent(this);
        }
        else if(middle==null)
        {
            if(left.getName().equals(newChild.getName()))
                throw new SameNameException("Directory already contains a file "
                        + "with the same name.");
            middle=newChild;
            middle.setParent(this);
        }
        else if(right==null)
        {
            if(middle.getName().equals(newChild.getName()) ||
                    left.getName().equals(newChild.getName()))
                throw new SameNameException("Directory already contains a file "
                        + "with the same name.");
            right=newChild;
            right.setParent(this);
        }
        else
            throw new FullDirectoryException();
    }
}