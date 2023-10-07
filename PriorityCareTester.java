//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PriorityCareTester
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
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester {

  /**
   * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
   * higher triage level is compared to a lower triage level, regardless of patient order of
   * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
   * returns a negative integer when a lower triage level is compared to a higher triage level,
   * regardless of patient order of arival.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToDifferentTriage() {
    // TODO complete the implementation of this tester method
    PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);
    PatientRecord yellowPatient1 = new PatientRecord('F', 24, TriageLevel.YELLOW);
    PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);


    // Compare triage levels
    int redYellowCompare = redPatient1.compareTo(yellowPatient1);
    int yellowGreenCompare = yellowPatient1.compareTo(greenPatient1);
    int redGreenCompare = redPatient1.compareTo(greenPatient1);

    if (redYellowCompare >= 0 || yellowGreenCompare >= 0 || redGreenCompare >= 0) {
      return false;
    }

    // different levels, reverse order
    int yellowRedCompare = yellowPatient1.compareTo(redPatient1);
    int greenYellowCompare = greenPatient1.compareTo(yellowPatient1);
    int greenRedCompare = greenPatient1.compareTo(redPatient1);

    if (yellowRedCompare <= 0 || greenYellowCompare <= 0 || greenRedCompare <= 0) {
      return false;
    }


    return true;
  }

  /**
   * Tests whether patients in the same triage level are compared based on their order of arrival.
   * Patients of the same triage level with a lower arrival number compared to patients with a
   * higher arrival number should return a negative integer. The reverse situation should return a
   * positive integer.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
    // TODO complete the implementation of this tester method
    PatientRecord.resetCounter();

    PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);
    PatientRecord yellowPatient1 = new PatientRecord('F', 24, TriageLevel.YELLOW);
    PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);
    PatientRecord redPatient2 = new PatientRecord('F', 30, TriageLevel.RED);
    PatientRecord yellowPatient2 = new PatientRecord('M', 20, TriageLevel.YELLOW);
    PatientRecord greenPatient2 = new PatientRecord('F', 11, TriageLevel.GREEN);

    int redRedCompare = redPatient1.compareTo(redPatient2);
    int yellowYellowCompare = yellowPatient1.compareTo(yellowPatient2);
    int greenGreenCompare = greenPatient1.compareTo(greenPatient2);

    // Compare based on arrival order, negative integer
    if (redRedCompare >= 0 || yellowYellowCompare >= 0 || greenGreenCompare >= 0) {
      return false;
    }

    // reverse, positive integer
    int redRedCompare1 = redPatient2.compareTo(redPatient1);
    int yellowYellowCompare1 = yellowPatient2.compareTo(yellowPatient1);
    int greenGreenCompare1 = greenPatient2.compareTo(greenPatient1);

    if (redRedCompare1 <= 0 || yellowYellowCompare1 <= 0 || greenGreenCompare1 <= 0) {
      return false;
    }

    return true;
  }

  /**
   * Tests whether patients in the same triage level and with the same order of arrival are equal
   * (compareTo should return 0). Even though this case will not be possible in your priority queue,
   * it is required for testing the full functionality of the compareTo() method. Hint: you will
   * need to use the resetCounter() to create equivalent PatientRecords.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageSameArrival() {
    // TODO complete the implementation of this tester method
    PatientRecord.resetCounter();
    PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);
    PatientRecord yellowPatient1 = new PatientRecord('F', 24, TriageLevel.YELLOW);
    PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);
    PatientRecord.resetCounter();
    PatientRecord redPatient2 = new PatientRecord('F', 30, TriageLevel.RED);
    PatientRecord yellowPatient2 = new PatientRecord('M', 20, TriageLevel.YELLOW);
    PatientRecord greenPatient2 = new PatientRecord('F', 11, TriageLevel.GREEN);

    int redRedCompare = redPatient1.compareTo(redPatient2);
    int yellowYellowCompare = yellowPatient1.compareTo(yellowPatient2);
    int greenGreenCompare = greenPatient1.compareTo(greenPatient2);

    if (yellowYellowCompare != 0 || greenGreenCompare != 0 || redRedCompare != 0) {
      return false;
    }



    return true;
  }

  /**
   * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
   * the following tests:
   *
   * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
   * IllegalArgumentException - Calling the PriorityCareAdmissions with a valid capacity should not
   * throw any errors, and should result in a new PriorityCareAdmissions which is empty, has size 0,
   * a capacity equal to the capacity that was passed as a parameter.
   *
   * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
   * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
   */
  public static boolean testConstructor() {
    // TODO complete the implementation of this tester method
    // Invalid capacity
    try {
      PriorityCareAdmissions queue = new PriorityCareAdmissions(-1);
      return false;
    } catch (Exception e) {

    }

    // valid capacity
    int capacity = 10;
    PriorityCareAdmissions queue1 = new PriorityCareAdmissions(capacity);
    if (queue1.size() != 0 || queue1.capacity() != capacity || !queue1.isEmpty()) {
      return false;
    }
    return true; // default return statement added to resolve compiler errors
  }


  /**
   * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
   * throws a NoSuchElementException.
   * 
   * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
   */
  public static boolean testPeekEmpty() {
    // TODO complete the implementation of this tester method
    PriorityCareAdmissions queue = new PriorityCareAdmissions(10);

    try {
      queue.peek();
      return false;
    } catch (Exception e) {

    }

    return true;
  }

  /**
   * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
   * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
   * the PatientRecord from the queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekNonEmpty() {
    // TODO complete the implementation of this tester method
    PatientRecord.resetCounter();
    PriorityCareAdmissions queue = new PriorityCareAdmissions(10);
    PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);
    PatientRecord yellowPatient1 = new PatientRecord('F', 24, TriageLevel.YELLOW);
    PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);
    queue.addPatient(redPatient1);
    queue.addPatient(yellowPatient1);
    queue.addPatient(greenPatient1);

    PatientRecord root = queue.peek();

    if (!root.equals(redPatient1)) {
      return false;
    }

    if (queue.size() != 3) {
      return false;
    }


    return true;
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
   * ensuring the method 1) adds the PatientRecord and 2) increments the size.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientEmpty() {
    // TODO complete the implementation of this tester method
    try {
      PatientRecord.resetCounter();
      PriorityCareAdmissions queue = new PriorityCareAdmissions(10);
      PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);

      queue.addPatient(greenPatient1);
      if (!queue.peek().equals(greenPatient1)) {
        return false;
      }

      if (queue.size() != 1) {
        return false;
      }

      // Invalid
      try {
        PriorityCareAdmissions queue1 = new PriorityCareAdmissions(-1);
        PatientRecord greenPatient2 = new PatientRecord('M', 12, TriageLevel.GREEN);
        queue1.addPatient(greenPatient2);
        return false;
      } catch (Exception e) {

      }



      return true;

    } catch (Exception e) {
      return false;
    }
  }



  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try add at least 5 PatientRecords.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  public static boolean testAddPatientNonEmpty() {
    // TODO complete the implementation of this tester method
    try {

      try {
        PriorityCareAdmissions queue4 = new PriorityCareAdmissions(5);
        PatientRecord.resetCounter();
        queue4.addPatient(null);
        return false;
      } catch (Exception e) {

      }
      PriorityCareAdmissions queue = new PriorityCareAdmissions(5);
      PatientRecord.resetCounter();
      PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);
      PatientRecord yellowPatient1 = new PatientRecord('F', 24, TriageLevel.YELLOW);
      PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);
      PatientRecord redPatient2 = new PatientRecord('F', 30, TriageLevel.RED);
      PatientRecord yellowPatient2 = new PatientRecord('M', 20, TriageLevel.YELLOW);

      queue.addPatient(redPatient1);
      queue.addPatient(yellowPatient1);
      queue.addPatient(greenPatient1);
      queue.addPatient(redPatient2);
      queue.addPatient(yellowPatient2);


      if (queue.size() != 5) {
        return false;
      }

      if (!queue.peek().equals(redPatient1)) {
        return false;
      }

      try {
        PatientRecord yellowPatient4 = new PatientRecord('M', 23, TriageLevel.YELLOW);
        queue.addPatient(yellowPatient4);
        return false;
      } catch (IllegalStateException e) {

      } catch (Exception e) {
        return false;
      }

      // Make sure minimum works
      PriorityCareAdmissions queue2 = new PriorityCareAdmissions(5);
      PatientRecord.resetCounter();
      PatientRecord yellowPatient = new PatientRecord('F', 24, TriageLevel.YELLOW);
      PatientRecord greenPatient = new PatientRecord('M', 12, TriageLevel.GREEN);
      PatientRecord greenPati1 = new PatientRecord('M', 22, TriageLevel.GREEN);
      PatientRecord yellowPat1 = new PatientRecord('M', 20, TriageLevel.YELLOW);

      queue2.addPatient(yellowPatient);
      queue2.addPatient(greenPatient);
      queue2.addPatient(greenPati1);
      queue2.addPatient(yellowPat1);

      if (queue2.size() != 4) {
        return false;
      }

      if (!queue2.peek().equals(yellowPatient)) {
        return false;
      }

      PatientRecord redPat1 = new PatientRecord('M', 40, TriageLevel.RED);

      queue2.addPatient(redPat1);

      if (!queue2.peek().equals(redPat1)) {
        return false;
      }

      if (queue2.size() != 5) {
        return false;
      }

      String expected = "24006: 40M (RED) - not seen\n" + "12402: 24F (YELLOW) - not seen\n"
          + "22005: 20M (YELLOW) - not seen\n" + "21203: 12M (GREEN) - not seen\n"
          + "22204: 22M (GREEN) - not seen";

      String actual = queue2.toString();

      if (!expected.equals(actual)) {
        return false;
      }
      // Adds at proper position
      PriorityCareAdmissions queue1 = new PriorityCareAdmissions(5);

      PatientRecord.resetCounter();
      PatientRecord greenPat1 = new PatientRecord('M', 22, TriageLevel.GREEN);
      PatientRecord greenPatient2 = new PatientRecord('F', 11, TriageLevel.GREEN);
      PatientRecord greenPatient3 = new PatientRecord('M', 10, TriageLevel.GREEN);

      queue1.addPatient(greenPat1);
      queue1.addPatient(greenPatient2);
      queue1.addPatient(greenPatient3);

      if (!queue1.peek().equals(greenPat1)) {
        return false;
      }

      if (queue1.size() != 3) {
        return false;
      }

      // Invalid
      try {
        PriorityCareAdmissions queue3 = new PriorityCareAdmissions(-1);
        PatientRecord greenPatient4 = new PatientRecord('M', 12, TriageLevel.GREEN);
        queue3.addPatient(greenPatient4);
        return false;
      } catch (Exception e) {

      }
      return true;



    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
   * ensuring the method throws an IllegalStateException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientFull() {
    // TODO complete the implementation of this tester method
    PriorityCareAdmissions queue = new PriorityCareAdmissions(5);
    PatientRecord.resetCounter();
    PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);
    PatientRecord yellowPatient1 = new PatientRecord('F', 24, TriageLevel.YELLOW);
    PatientRecord greenPatient1 = new PatientRecord('M', 12, TriageLevel.GREEN);
    PatientRecord redPatient2 = new PatientRecord('F', 30, TriageLevel.RED);
    PatientRecord yellowPatient2 = new PatientRecord('M', 20, TriageLevel.YELLOW);

    queue.addPatient(redPatient1);
    queue.addPatient(yellowPatient1);
    queue.addPatient(greenPatient1);
    queue.addPatient(redPatient2);
    queue.addPatient(yellowPatient2);

    try {
      PatientRecord greenPatient2 = new PatientRecord('F', 11, TriageLevel.GREEN);
      queue.addPatient(greenPatient2);
      return false;
    } catch (IllegalStateException e) {

    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() with a null
   * PatientRecord and ensuring the method throws a NullPointerException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientNull() {
    // TODO complete the implementation of this tester method
    try {
      PriorityCareAdmissions queue = new PriorityCareAdmissions(5);
      queue.addPatient(null);
      return false;

    } catch (Exception e) {

    }



    return true;
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
   * queue.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
   *         false otherwise
   */
  public static boolean testRemoveBestRecordEmpty() {
    // TODO complete the implementation of this tester method

    PriorityCareAdmissions queue = new PriorityCareAdmissions(5);
    try {
      queue.removeBestRecord();
      return false;
    } catch (Exception e) {

    }


    return true; // default return statement added to resolve compiler errors
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
   * of size one.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
   *         size is 0
   */
  public static boolean testRemoveBestRecordSizeOne() {
    // TODO complete the implementation of this tester method
    PriorityCareAdmissions queue = new PriorityCareAdmissions(5);
    PatientRecord.resetCounter();
    PatientRecord redPatient1 = new PatientRecord('M', 40, TriageLevel.RED);

    queue.addPatient(redPatient1);

    PatientRecord removedPatient = queue.removeBestRecord();

    if (!removedPatient.equals(redPatient1)) {
      return false;
    }

    if (queue.size() != 0) {
      return false;
    }



    return true; // default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of removeBestRecord() methods.
   * 
   * The removeBestRecord() method must remove, and return the patient record with the highest
   * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
   * method is successfully called.
   * 
   * Remove the best record from a queue whose size is at least 6. Consider cases where
   * percolate-down recurses on left and right.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testRemoveBestRecordNonEmpty() {
    try {
      // TODO complete the implementation of this tester method // recurses left
      PriorityCareAdmissions queue = new PriorityCareAdmissions(6);
      PatientRecord.resetCounter();
      PatientRecord p1 = new PatientRecord('F', 25, TriageLevel.RED);
      PatientRecord p2 = new PatientRecord('M', 30, TriageLevel.YELLOW);
      PatientRecord p3 = new PatientRecord('F', 35, TriageLevel.GREEN);
      PatientRecord p4 = new PatientRecord('M', 40, TriageLevel.YELLOW);
      PatientRecord p5 = new PatientRecord('F', 45, TriageLevel.GREEN);
      PatientRecord p6 = new PatientRecord('M', 50, TriageLevel.GREEN);
      queue.addPatient(p1);
      queue.addPatient(p2);
      queue.addPatient(p3);
      queue.addPatient(p4);
      queue.addPatient(p5);
      queue.addPatient(p6);


      if (!queue.removeBestRecord().equals(p1) || queue.size() != 5) {
        return false;
      }
      if (!queue.peek().equals(p2)) {
        return false;
      }
      if (!queue.removeBestRecord().equals(p2) || queue.size() != 4) {
        return false;
      }
      if (!queue.removeBestRecord().equals(p4) || queue.size() != 3) {
        return false;
      }
      if (!queue.removeBestRecord().equals(p3) || queue.size() != 2) {
        return false;
      }
      if (!queue.removeBestRecord().equals(p5) || queue.size() != 1) {
        return false;
      }
      if (!queue.removeBestRecord().equals(p6) || queue.size() != 0) {
        return false;
      }
      try {
        queue.removeBestRecord();
        return false;
      } catch (NoSuchElementException e) {
        // expected exception thrown when trying to remove from an empty queue
      }

      // recurses right
      PriorityCareAdmissions queue2 = new PriorityCareAdmissions(6);
      PatientRecord.resetCounter();
      PatientRecord p7 = new PatientRecord('F', 25, TriageLevel.RED);
      PatientRecord p8 = new PatientRecord('M', 30, TriageLevel.GREEN);
      PatientRecord p9 = new PatientRecord('F', 35, TriageLevel.YELLOW);
      PatientRecord p10 = new PatientRecord('M', 40, TriageLevel.YELLOW);
      PatientRecord p11 = new PatientRecord('F', 45, TriageLevel.GREEN);
      PatientRecord p12 = new PatientRecord('M', 50, TriageLevel.RED);
      queue2.addPatient(p7);
      queue2.addPatient(p8);
      queue2.addPatient(p9);
      queue2.addPatient(p10);
      queue2.addPatient(p11);
      queue2.addPatient(p12);

      queue2.removeBestRecord();

      if (!queue2.peek().equals(p6)) {
        return false;
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios: - clear can be called on an empty queue with no errors - clear can be called on a
   * non-empty queue with no errors - After calling clear(), the queue should contain zero
   * PatientRecords. - After calling clear(), the size should be 0
   *
   * @return true if PriorityCareAdmissions.clear() functions properly
   */
  public static boolean testClear() {
    // TODO complete the implementation of this tester method
    PriorityCareAdmissions queue = new PriorityCareAdmissions(6);
    PatientRecord.resetCounter();
    PatientRecord p1 = new PatientRecord('F', 25, TriageLevel.RED);
    PatientRecord p2 = new PatientRecord('M', 30, TriageLevel.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 35, TriageLevel.GREEN);
    PatientRecord p4 = new PatientRecord('M', 40, TriageLevel.YELLOW);
    PatientRecord p5 = new PatientRecord('F', 45, TriageLevel.GREEN);
    PatientRecord p6 = new PatientRecord('M', 50, TriageLevel.RED);

    queue.addPatient(p1);
    queue.addPatient(p2);
    queue.addPatient(p3);
    queue.addPatient(p4);
    queue.addPatient(p5);
    queue.addPatient(p6);

    // clear can be called on a non-empty
    try {
      queue.clear();
    } catch (Exception e) {
      return false;
    }

    if (queue.size() != 0) {
      return false;
    }

    queue.addPatient(p1);
    queue.clear();
    // size should be 0
    if (queue.size() != 0) {
      return false;
    }
    // should return zero patient records
    try {
      queue.peek();
      return false;
    } catch (Exception e) {

    }
    // Clear can be done on an empty
    try {
      queue.clear();
    } catch (Exception e) {
      return false;
    }

    return true; // default return statement added to resolve compiler errors
  }


  /**
   * Tests toString() method of PriorityCareAdmissions class.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testToString() {
    // TODO complete the implementation of this tester method
    PriorityCareAdmissions queue = new PriorityCareAdmissions(3);
    PatientRecord.resetCounter();
    PatientRecord p1 = new PatientRecord('F', 25, TriageLevel.RED);
    PatientRecord p2 = new PatientRecord('M', 30, TriageLevel.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 35, TriageLevel.GREEN);

    queue.addPatient(p1);
    queue.addPatient(p2);
    queue.addPatient(p3);

    p2.seePatient();

    String actual = queue.toString();
    String expected = "12502: 25F (RED) - not seen\n" + "23003: 30M (YELLOW) - seen\n"
        + "13504: 35F (GREEN) - not seen";



    if (!expected.equals(actual)) {
      return false;
    }



    return true; // default return statement added to resolve compiler errors
  }


  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {

    return testPatientRecordCompareToDifferentTriage()
        && testPatientRecordCompareToSameTriageDifferentArrival()
        && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
        && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
        && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
        && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
        && testToString();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToDifferentTriage: "
        + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
        + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
        + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
    System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
    System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
    System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
  }

}
