/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.utilities;

public class StringConstants {

    private StringConstants(){}

    // Encryption Params
    public static final String ENCRYPTION_PASSWORD = "ENCRYPTION_PASSWORD";
    public static final String ENCRYPTION_ALGORITHM = "ENCRYPTION_ALGORITHM";
    public static final String ENCRYPTION_ITERATIONS = "ENCRYPTION_ITERATIONS";
    public static final String ENCRYPTION_POOL_SIZE = "ENCRYPTION_POOL_SIZE";
    public static final String ENCRYPTION_PROVIDER_NAME = "ENCRYPTION_PROVIDER_NAME";
    public static final String ENCRYPTION_SALT_GENERATOR = "ENCRYPTION_SALT_GENERATOR";
    public static final String ENCRYPTION_OUTPUT_TYPE = "ENCRYPTION_OUTPUT_TYPE";

    // Mapper and Database error messages
    public static final String MAPPING_ERROR = "Error occurred while mapping";
    public static final String DATABASE_ERROR = "Error occurred while database operation";
    public static final String FILE_ERROR = "Error occurred while creating file";
    public static final String DUPLICATE_ERROR = "Duplicate name";
    public static final String DUPLICATE_EMAIL = "Duplicate email";
    public static final String UPDATE_ERROR = "Update failed";
    public static final String REQUEST_PROCESSED = "Request Processed";
    public static final String ID_NOT_PRESENT = "Id not present in Database";
    public static final String EMAIL_NOT_PRESENT = "Email not present in Database";
    public static final String NAME_NOT_PRESENT = "Name not present in Database";
    public static final String FILE_NOT_PRESENT = "File not present";
    public static final String IS_A_VIEWER = "User is a viewer";
    public static final String NOT_A_VIEWER = "User is not a viewer";
    public static final String MAPPING_ERROR_MODEL_TO_ENTITY = "Error occurred while converting model to entity";
    public static final String MAPPING_ERROR_ENTITY_TO_MODEL = "Error occurred while converting entity to model";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE = "ROLE";

    public static final String LEGAL_FOLDER_PATH="/Jack/ESportsManagementSystem/DOCUMENTS/LEGAL/";
    public static final String LOGO_FOLDER_PATH="/Jack/ESportsManagementSystem/DOCUMENTS/LOGO/";
    public static final String LEADERBOARD_FOLDER_PATH="/Jack/ESportsManagementSystem/DOCUMENTS/LEADERBOARD/";
    public static final String CONGRATULATIONS = "Congratulations";
    public static final String BETTER_LUCK = "Better luck next time";

}
