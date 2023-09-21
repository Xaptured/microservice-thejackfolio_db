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
    public static final String REQUEST_PROCESSED = "Request Processed";
    public static final String ID_NOT_PRESENT = "Id not present in Database";
    public static final String MAPPING_ERROR_MODEL_TO_ENTITY = "Error occurred while converting model to entity";
    public static final String MAPPING_ERROR_ENTITY_TO_MODEL = "Error occurred while converting entity to model";

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE = "ROLE";

}
