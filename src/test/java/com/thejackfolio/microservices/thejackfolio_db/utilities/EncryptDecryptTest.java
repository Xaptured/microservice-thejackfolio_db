/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.utilities;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

/**
 * The class type Encrypt decrypt test.
 *
 * Use this test file to generate encrypted username and password and set them inside application.properties for h2-console.
 * Please use ENC(your-encrypted-value) to make spring-boot understand the provided string is encrypted, and it will decrypt, according to the encryption algorithm that you provided
 */
class EncryptDecryptTest {

//    public StringEncryptor getPasswordEncryptor() {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        config.setPassword(PropertiesReader.getProperty(StringConstants.ENCRYPTION_PASSWORD));
//        config.setAlgorithm(PropertiesReader.getProperty(StringConstants.ENCRYPTION_ALGORITHM));
//        config.setKeyObtentionIterations(PropertiesReader.getProperty(StringConstants.ENCRYPTION_ITERATIONS));
//        config.setPoolSize(PropertiesReader.getProperty(StringConstants.ENCRYPTION_POOL_SIZE));
//        config.setProviderName(PropertiesReader.getProperty(StringConstants.ENCRYPTION_PROVIDER_NAME));
//        config.setSaltGeneratorClassName(PropertiesReader.getProperty(StringConstants.ENCRYPTION_SALT_GENERATOR));
//        config.setStringOutputType(PropertiesReader.getProperty(StringConstants.ENCRYPTION_OUTPUT_TYPE));
//        encryptor.setConfig(config);
//
//        return encryptor;
//    }
//    @Test
//    void encrypt_test() {
//        String cypherText = getPasswordEncryptor().encrypt("YOUR-PLAIN-TEXT");
//        System.out.println(cypherText);
//    }
//
//    @Test
//    void decrypt_test() {
//        String plainText = getPasswordEncryptor().decrypt("4AD/GzIynZmv50z/esZ/93EKGgWqNaAN");
//        System.out.println(plainText);
//    }

}