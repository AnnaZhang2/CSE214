/**
 * A class that implements a ternary (3-child) tree of DirectoryNodes. This
 * class contains a reference to the root of the tree, a cursor for the present
 * working directory, and various methods for insertion and deletion. It should
 * provide an implementation for the operations defined for the system. It
 * contains methods for moving the cursor through the file system, printing the
 * filepath of the present working directory (cursor location), listing the
 * directories and files in the present working directory, printing the entire
 * file system, and finding a file in the file system.
 *
 *  @author Anna Zhang
 *  email: anna.zhang.2@stonybrook.edu
 *  section: 06
 *  Stony Brook ID: 112167606
 */

public class DirectoryTree
{
    private DirectoryNode root;
    private DirectoryNode cursor;

    /**
     * Initializes a DirectoryTree object with a single DirectoryNode named
     * "root"
     *
     * Preconditions:
     *  none
     *
     * Postconditions:
     *  The tree contains a single DirectoryNode named "root", and both cursor
     *  and root reference this node.
     */
    public DirectoryTree()
    {
        root = new DirectoryNode("root", false);
        root.setParent(null);
        cursor = root;
    }

    /**
     * Returns the root of the tree
     *
     * @return
     *  The root of the tree.
     */
    public DirectoryNode getRoot()
    {
        return root;
    }

    /**
     * Returns the cursor of the tree
     *
     * @return
     *  The cursor of the tree.
     */
    public DirectoryNode getCursor()
    {
        return cursor;
    }

    /**
     * Moves the cursor to the root node of the tree.
     *
     * Preconditions:
     *  none
     *
     * Postconditions:
     *  The cursor now references the root node of the tree.
     */
    public void resetCursor()
    {
        cursor = root;
    }

    /**
     * Moves the cursor to the directory with the name indicated by name.
     *
     * Preconditions:
     *  "name" references a valid directory ("name" cannot reference a file)
     *
     * Postconditions:
     *  The cursor now references the directory with the name indicated by name.
     *  If a child could not be found with that name, then the user is prompted
     *  to enter a different directory name. If the name was not a directory, a
     *  NotADirectoryException has been thrown.
     *
     * @param name
     *  The name that references a directory
     *
     * @throws NotADirectoryException
     *  Thrown if the node with the indicated name is a file, as files cannot be
     *  selected by the cursor or cannot be found
     */
    public void changeDirectory(String name) throws NotADirectoryException
    {
        if(cursor!=null)
        {
            if(cursor.getLeft()!=null&&cursor.getLeft().getName().equals(name))
            {
                if(cursor.getLeft().getIsFile()==false)
                {
                    cursor = cursor.getLeft();
                }
                else
                    throw new NotADirectoryException();
            }
            else if(cursor.getMiddle()!=null&&cursor.getMiddle().getName()
                    .equals(name))
            {
                if(cursor.getMiddle().getIsFile()==false)
                {
                    cursor = cursor.getMiddle();
                }
                else
                    throw new NotADirectoryException();
            }
            else if(cursor.getRight()!=null&&cursor.getRight().getName()
                    .equals(name))
            {
                if(cursor.getRight().getIsFile()==false)
                {
                    cursor = cursor.getRight();
                }
                else
                    throw new NotADirectoryException();
            }
            else
                System.out.println("ERROR: No such directory named '"
                        + name +"'.");
        }
    }

    /**
     * Moves the cursor to the directory using the indicated path.
     *
     * @param path
     *  A String that provides the path that the cursor has to follow
     *
     * @throws NotADirectoryException
     *  Thrown if the node with the indicated name is a file, as files cannot be
     *  selected by the cursor or cannot be found
     */
    public void changeDirectoryPath(String path) throws NotADirectoryException
    {
        int index=path.indexOf("/");

        //absolute path with root in the beginning
        if(path.startsWith("root/"))
        {
            resetCursor();
            path=path.substring(index+1);
            index=path.indexOf("/");
            while(index!=-1)
            {
                changeDirectory(path.substring(0, index));
                path=path.substring(index+1);
                index=path.indexOf("/");
            }
            if(!path.isEmpty())
                changeDirectory(path);
        }
        //relative path starting at the cursor
        else
        {
            if(index==-1)
                changeDirectory(path);
            else
            {
                while(index!=-1)
                {
                    changeDirectory(path.substring(0,index));
                    path=path.substring(index+1);
                    index=path.indexOf("/");
                }
                if(!path.isEmpty())
                    changeDirectory(path);
            }
        }
    }

    /**
     * Returns a String containing the path of directory names from the root
     * node of the tree with each name separated by a forward slash "/"
     *
     * Preconditions:
     *  None.
     *
     * Postconditions:
     *  The cursor remains at the same DirectoryNode.
     *
     * @return
     *  String that contains the path of directory names from the root node of
     *  the tree to the cursor
     */
    public String presentWorkingDirectory()
    {
        String pwd = presentWorkingDirectory(cursor);
        String output = "";
        while(pwd.indexOf("/")!=-1)
        {
            int i = pwd.lastIndexOf("/");
            output+=pwd.substring(i+1)+"/";
            pwd=pwd.substring(0,i);
        }
        output+=pwd.substring(0);
        return output.substring(1);
    }

    /**
     * A helper method which returns a String containing the path of directory
     * names from the root with each name separated by a forward slash.
     *
     * @param current
     *  The node that is pointed at starting from the cursor to the root.
     *
     * @return
     *  A String that contains the path of directory names from the root to the
     *  cursor.
     */
    public String presentWorkingDirectory(DirectoryNode current)
    {
        if(root==null||current==null)
            return null;

        String output="";

        while(current!=null)
        {
            output+=current.getName()+"/";
            current=current.getParent();
        }

        return output;
    }

    /**
     * Returns a String containing a space-separated list of names of all the
     * child directories or files of the cursor.
     *
     * Preconditions:
     *  None.
     *
     * Postconditions:
     *  The cursor remains at the same DirectoryNode.
     *
     * @return
     *  A formatted String of DirectoryNode names.
     */
    public String listDirectory()
    {
        String output = "";

        if(cursor.getLeft()!=null)
            output+=cursor.getLeft().getName()+" ";
        if(cursor.getMiddle()!=null)
            output+=cursor.getMiddle().getName()+" ";
        if(cursor.getRight()!=null)
            output+=cursor.getRight().getName();

        return output;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory
     * tree, starting from the cursor.
     *
     * Preconditions:
     *  None.
     *
     * Postconditions:
     *  The cursor remains at the same DirectoryNode.
     */
    public void printDirectoryTree()
    {
        printDirectoryTree(cursor, 0);
    }

    /**
     * Helper method for printDirectoryTree
     *
     * @param current
     *  The node that the method is currently working with
     *
     * @param depth
     *  The level of depth of the tree
     */
    public void printDirectoryTree(DirectoryNode current, int depth)
    {
        if(root==null)
            return;

        String output = "";
        String tab = "";

        for(int i=0; i<depth; i++)
            tab+="    ";

        if(current.getIsFile()==false)
            output+=tab + "|- " + current.getName();
        else
            output+=tab + "- " + current.getName();

        System.out.println(output);

        if(current.getLeft()!=null)
            printDirectoryTree(current.getLeft(), depth+1);
        if(current.getMiddle()!=null)
            printDirectoryTree(current.getMiddle(), depth+1);
        if(current.getRight()!=null)
            printDirectoryTree(current.getRight(), depth+1);
    }

    /**
     * Creates a directory with the indicated name and adds it to the children
     * of the cursor node.
     *
     * @param name
     *  The name of the directory to add.
     *
     * Preconditions:
     *  "name" is a legal argument (does not contain spaces or forward slashes
     *
     * Postconditions:
     *  A new DirectoryNode has been added to the children of the cursor, or an
     *  exception has been thrown.
     *
     * @throws IllegalArgumentException
     *  Thrown if the "name" argument is invalid.
     *
     */
    public void makeDirectory(String name) throws IllegalArgumentException,
            FullDirectoryException, NotADirectoryException
    {
        try
        {
            DirectoryNode newChild = new DirectoryNode(name, false);
            cursor.addChild(newChild);
        }
        catch(SameNameException e)
        {
            System.out.println("A directory with the same name already "
                    + "exists.");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Directory already contains a file with the same"
                    + " name.");
        }
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the
     * cursor node.
     *
     * @param name
     *  The name of the file to be added.
     *
     * Preconditions:
     *  "name" is a legal argument (does not contain spaces or forward slashes)
     *
     * Postconditions:
     *  A new DirectoryNode has been added to the children of the cursor, or an
     *  exception has been thrown.
     *
     * @throws IllegalArgumentException
     *  Thrown if the "name" argument is invalid.
     *
     * @throws FullDirectoryException
     *  Thrown if all child references of this directory are occupied.
     */
    public void makeFile(String name) throws IllegalArgumentException,
            FullDirectoryException
    {
        try
        {
            DirectoryNode newChild = new DirectoryNode(name, true);
            cursor.addFile(newChild);
        }
        catch(SameNameException e)
        {
            System.out.println("Directory already contains a file with the same"
                    + " name.");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Name should not contain spaces or forward "
                    + "slashes.");
        }
    }

    /**
     * Moves the cursor to its parent node
     */
    public void moveCursorUp()
    {
        if(cursor==root)
            System.out.println("ERROR: Already at root directory.");
        else if(cursor.getParent()!=null)
            cursor=cursor.getParent();
    }
}