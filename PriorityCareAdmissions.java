//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PriorityCareAdmissions
// Course: CS 300 Spring 2023
//
// Author: Om Thakkar
// Email: othakkar@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:
// Partner Email:
// Partner Lecturer's Name:
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords. The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
  private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
                                 // queue
  private int size; // size of this priority queue


  /**
   * Creates a new empty PriorityCareAdmissions queue with the given capacity
   * 
   * @param capacity Capacity of this PriorityCareAdmissions queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public PriorityCareAdmissions(int capacity) throws IllegalArgumentException {
    // TODO complete this implementation
    if (capacity <= 0) {
      throw new IllegalArgumentException("Invalid capacity");
    }
    this.queue = new PatientRecord[capacity];
    this.size = 0;

  }

  /**
   * Checks whether this PriorityCareAdmissions queue is empty
   * 
   * @return {@code true} if this PriorityCareAdmissions queue is empty
   */
  public boolean isEmpty() {
    // TODO complete this implementation

    return size == 0; // default return statement added to resolve compile errors
  }

  /**
   * Returns the size of this PriorityCareAdmissions queue
   * 
   * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
   */
  public int size() {
    // TODO complete this implementation

    return this.size; // default return statement added to resolve compile errors
  }

  /**
   * Returns the capacity of this PriorityCareAdmissions queue
   * 
   * @return the capacity of this PriorityCareAdmissions queue
   */
  public int capacity() {
    // TODO complete this implementation

    return queue.length; // default return statement added to resolve compile errors
  }


  /**
   * Removes all the elements from this PriorityCareAdmissions queue
   */
  public void clear() {
    // TODO complete this implementation
    for (int i = 0; i < size; i++) {
      queue[i] = null;
    }
    size = 0;
  }

  /**
   * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
   * PatientRecord having the the highest priority.
   * 
   * @return the PatientRecord at the root of this PriorityCareAdmissions queue
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord peek() throws NoSuchElementException {
    // TODO complete this implementation
    if (isEmpty()) {
      throw new NoSuchElementException("Warning: Empty Admissions Queue!");
    }


    return queue[0]; // default return statement added to resolve compile errors
  }


  /**
   * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position based
   * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
   * PatientRecord at each index is less than or equal to than the PatientRecords in its child
   * nodes. PatientRecords should be compared using the PatientRecord.compareTo() method.
   * 
   * @param p PatientRecord to add to this PriorityCareAdmissions queue
   * @throws NullPointerException  if the given PatientRecord is null
   * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
   *                               if this PriorityCareAdmissions queue is full
   */
  public void addPatient(PatientRecord p) throws NullPointerException, IllegalStateException {
    if (p == null) {
      throw new NullPointerException("PatientRecord is null");
    }

    if (size == capacity()) {
      throw new IllegalStateException("Warning: Full Admissions Queue!");
    }

    queue[size] = p;
    size++;
    percolateUp(size - 1);


    // TODO complete this implementation
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
   * priority queue by percolating a leaf up the heap. If the element at the given index does not
   * violate the min-heap invariant (it is greater than its parent), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
   * continue percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateUp(int i) throws IndexOutOfBoundsException {
    // TODO complete this implementation. To get more practice on recursive thinking, implemented
    // this method recursively
    if (i < 0 || i >= size()) {
      throw new IndexOutOfBoundsException("Index is out of bound");
    }

    if (i == 0) { // base case
      return;
    }

    int parentIndex = (i - 1) / 2;
    if (queue[i].compareTo(queue[parentIndex]) < 0) {
      PatientRecord tmp = queue[i];
      queue[i] = queue[parentIndex];
      queue[parentIndex] = tmp;
      percolateUp(parentIndex);

    }

  }


  /**
   * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
   * the PatientRecord having the highest priority (the minimum one).
   * 
   * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
   *         queue.
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord removeBestRecord() throws NoSuchElementException {
    // TODO complete this implementation
    if (isEmpty()) {
      throw new NoSuchElementException("Warning: Empty Admissions Queue!");
    }

    PatientRecord root = queue[0];

    queue[0] = queue[size() - 1];
    queue[size() - 1] = null;

    size--;

    percolateDown(0);
    return root; // default return statement added to resolve compile errors
  }


  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap ordering property (it is smaller than its smallest child), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateDown(int i) {
    // TODO complete this implementation. To get more practice on recursive thinking, implemented
    // this method recursively
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int smallest = i;

    if (left < size() && queue[left].compareTo(queue[smallest]) < 0) {
      smallest = left;
    }

    if (right < size() && queue[right].compareTo(queue[smallest]) < 0) {
      smallest = right;
    }

    if (smallest != i) {
      PatientRecord tmp = queue[i];
      queue[i] = queue[smallest];
      queue[smallest] = tmp;
      percolateDown(smallest);


    }

  }


  /**
   * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements in the
   * same order. This method does not return the deepest copy, meaning that you do not need to
   * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
   * be duplicated.
   * 
   * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
   *         admissions queue has the same length and size as this queue.
   */
  public PriorityCareAdmissions deepCopy() {
    PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
    deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
    deepCopy.size = this.size;
    return deepCopy;
  }

  /**
   * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the ParientRecords in this queue
   */
  protected PatientRecord[] arrayHeapCopy() {
    return Arrays.copyOf(this.queue, this.queue.length);

  }

  /**
   * Returns a String representing this PriorityCareAdmissions queue, where each element
   * (PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
   * 
   * @return a String representing this PriorityCareAdmissions queue, and an empty String "" if this
   *         queue is empty.
   */
  public String toString() {
    // TODO complete this implementation
    if (isEmpty()) {
      return "";
    }

    PriorityCareAdmissions copy = this.deepCopy();
    String out = "";

    while (!copy.isEmpty()) {
      out += copy.removeBestRecord().toString();
      if (!copy.isEmpty()) {
        out += "\n";
      }
    }
    // [HINT] use a loop to traverse a deepCopy of this priority queue by removing the best patient
    // record until the queue is empty.
    return out; // default return statement added to resolve compile errors

  }


}
