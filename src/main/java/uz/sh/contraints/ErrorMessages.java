package uz.sh.contraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author TheJWA (Jonibek)
 * @since 1/21/23/2:17 AM (Saturday)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessages {
    // Error messagelar shu yerda turishi kerak

    public static final String ROLE_NOT_FOUND = "ROLE NOT FOUND";
    public static final String ITEM_NOT_FOUND_WITH_THIS_ID = "Item not found with this id : ";
    public static final String ITEM_NOT_EXISTS_WITH_THIS_ID = "Item exists with this id : ";
    public static final String ROLE_EXISTS = "ROLE ALREADY EXISTS WITH GIVEN NAME";

    public static final String UNAUTHORIZED = "Please login first in order to access the resource! ";
    public static final String ACCESS_DENIED = "This resource is forbidden for this credentials! ";
    public static final String EXCEPTION_OCCURRED_WHILE_COMPRESSING_AND_SAVING_FILE_WITH_NAME = "Exception Occurred while compressing and saving this file with name : ";
    public static final String UNCOMPRESSIBLE_FILE_LOADED_WITH_NAME = "UnCompressible file tried to compress; Compressor for only .jpg, .jpeg, .png; FileName : ";
    public static final String EXCEPTION_OCCURRED_DOWNLOADING_FILE_WITH_ID = "Exception Occurred while downloading file with id :";
    public static final String EXCEPTION_OCCURRED_WHILE_DELETING_FILE_WITH_NAME = "Exception Occurred while deleting file with name : ";
    public static final String EXCEPTION_OCCURRED_WHILE_SAVING_FILE_WITH_NAME = "Exception Occurred while saving this file with name : ";
    public static final String CONFLICT_IN_LESSON_TIMES = "There is Time Conflicts with Group Lesson !!! Group name : ";
    public static final String THIS_STUDENT_ALREADY_STUDENT_IN_THIS_BRANCH = "This user already Student in this branch!";
    public static final String STUDENT_ALREADY_BIND_TO_THIS_GROUP = " Student already bind to this group ! Student id : ";
    public static final String STUDENT_IS_NOT_MEMBER_OF_THIS_GROUP = " This Student is not member of this group! Student id : ";

    public static final String DOES_NOT_BELONG_ORGANIZATION = "The user does not belong to this organization!!!";
    public static final String DOES_NOT_BELONG_BRANCH = "The user does not belong to this branch!!!";

    public static final String BRANCH_NOT_FOUND = "Branch not found: ";
    public static final String THIS_ORGANIZATION_HAS_AN_EMPLOYEE_POSITION_FOR_THE_USER_YOU_ARE_ENTERING = "This organization has an employee position for the user you are entering";
    public static final String USER_NOT_FOUND = "User not found. id: ";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found: ";
    public static final String STUDENT_ID_MUST_NOT_EXIST_FOR_TYPE_YOU_CHOOSE = "StudentId must not exist for the type you choose: ";
    public static final String STUDENT_NOT_FOUND = "Student not found";
    public static final String STUDENT_ID_MUST_EXIST_FOR_THIS_TYPE = "A studentId must exist for the type you select: ";
    public static final String INCOME_NOT_FOUND = "Income not found: ";
    public static final String LID_DEADLINE_INVALID = "Lid deadline invalid: ";
    public static final String LID_NOT_FOUND = "Lid not found: ";
    public static final String ROOM_NOT_FOUND = "Room not found: ";
    public static final String THIS_ROOM_CANNOT_BE_DELETED_BECAUSE_IT_HAS_A_GROUP_ATTACHED_TO_IT = "This room cannot be deleted because it has a group attached to it";
    public static final String YOU_CANNOT_SELECT_INCOME_TYPE_FOR_OUTPUT = "You cannot select income types for output type";
    public static final String STUDENT_PAYMENT_HISTORY_NOT_FOUND = "Student payment history not found";
}
