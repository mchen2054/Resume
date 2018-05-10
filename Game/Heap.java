package Game;

public class Heap 
{
	private final int MAXHEAP = 100; // Arbitrarily picked
	protected HeapEntry[] tree; // The array to hold the heap entries
	protected int insertPoint; // Location of the place to put an new entry
	protected int size;

	public Heap()
	// PRE: None
	// POS: Empty heap; insertPoint == 0;
	// TAS: Create a new heap
	{
		tree = new HeapEntry[MAXHEAP];
		insertPoint = 0;
		size = 0;
	}// Heap

	public boolean isEmpty() {
		return tree[0] == null;
	}

	public void print()
	// PRE: none
	// POS: none
	// TAS: debugging code. Not for use in final implementation
	{
		System.out.println("Heap print");
		for (int i = 0; i < insertPoint; i++)
		{
			System.out.println(tree[i].getElement());
			System.out.println(tree[i].getPriority());
		}
	}

	private void swap(int parent, int child)
	// PRE: two or more nodes in heap
	// POS: tree[parent]<exit> == tree[child]<entry>
	// tree[child]<exit> == tree[parent]<entry>
	// TAS: swap entries at parent and child
	{
		HeapEntry temp = new HeapEntry();
		temp.setElement(tree[parent].getElement());
		temp.setPriority(tree[parent].getPriority());

		tree[parent].setElement(tree[child].getElement());
		tree[parent].setPriority(tree[child].getPriority());

		tree[child].setElement(temp.getElement());
		tree[child].setPriority(temp.getPriority());
	}

	public void insert(Object newElement, int priority)
	// PRE: none
	// POS: tree<exit> == tree<entry> + newElement && tree is heap
	// POS: insertPoint<exit> == insertPoint<entry> + 1
	// TAS: insert newElement into tree heap
	{
		// Create the new HeapEntry
		tree[insertPoint] = new HeapEntry(newElement, priority);
		// Set up local variables
		boolean done = false;
		int parent = (insertPoint - 1) / 2; // nifty trick for finding parent's location
		int child = insertPoint;
		insertPoint++; // increase to the next insertion location
		size++;

		while ((parent >= 0) && !done) // start hunting for correct location
		{
			if (tree[parent].getPriority() < tree[child].getPriority()) {
				swap(parent, child);
				child = parent;
				parent = (parent - 1) / 2;
			} else {
				done = true;
			}

		} // while
	}// insert

	public Object peek()
	// PRE: none
	// POS: none
	// TAS: return the object at top of heap
	{
		if (tree == null)
			return null;
		else
			return tree[0].getElement();
	}

	public HeapEntry remove()
	// PRE: Tree is not empty
	// POS:
	// TAS:
	{

		HeapEntry root = tree[0];

		if (insertPoint == 1)
			tree[0] = null;
		else
			tree[0] = tree[insertPoint - 1];

		this.reHeapify();

		insertPoint--;
		size--;

		return root;
		// insert your code here
	}// remove

	private int findSwapChild(int left, int right) {
		int next = 0;
		if ((tree[left] == null) && (tree[right] == null))// you are done
			next = insertPoint; // stops method...see loop condition
		else if (tree[left] == null) // swap right
			next = right;
		else if (tree[right] == null) // swap left
			next = left;
		else if ((tree[left].getPriority() > tree[right].getPriority()))
			next = left; // left child is greater
		else
			next = right; // right child is greater
		return next;
	}

	private void reHeapify()
	// PRE: tree has at least one parent and one child
	// Reorders the tree to restore the order property
	{
		HeapEntry temp; // temporary storage
		int node = 0; // root of the subtree
		int left = 1; // left child of the subtree
		int right = 2; // right child of the subtree
		// int next; //The "next" root of the left or right subtree

		// Figure out which child--left or right to swap with
		int next = findSwapChild(left, right);

		// Start swap and drop
		while ((next < insertPoint) && (tree[next].getPriority() > tree[node].getPriority())) {
			// swap node and next
			temp = tree[node];
			tree[node] = tree[next];
			tree[next] = temp;

			// Determine where to go next with swap and drop
			node = next;
			left = 2 * node + 1;
			right = 2 * node + 2;
			next = findSwapChild(left, right);
		} // while
	}// reHeapify
	
	public int size() 
	{
		return size;
	}// size

}// Heap
