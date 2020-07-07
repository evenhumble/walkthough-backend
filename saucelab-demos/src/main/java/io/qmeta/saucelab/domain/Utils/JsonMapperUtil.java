package io.qmeta.saucelab.domain.Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Marina on 28/06/2017.
 */
public class JsonMapperUtil {


        public static HashMap< String,String> getParameterHashMapFromJsonFile(String pathname) {
            HashMap< String,String> parameterList = null;

            ObjectMapper mapper = new ObjectMapper();

            try {
                parameterList = mapper.readValue(new File(pathname),
                        new TypeReference<HashMap< String,String>>() {
                        });

            } catch (JsonGenerationException e) {
                System.err.println("The json configuration file is not valid. Please verify the file.");

            } catch (JsonMappingException  e) {
                System.err.println("The json configuration file is not valid. Please verify the file.");

            } catch (IOException e) {
                System.err.println("No configuration file available.");
            }
            return parameterList;

        }

    public static Object[][] getObjectArraysFromJsonFile(String pathname) {

        Object[][] browsersList = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            browsersList = mapper.readValue(new File(pathname),
                    new TypeReference<Object[][]>() {
                    });

        } catch (JsonGenerationException e) {
            System.err.println("The json configuration file is not valid. Please verify the file.");

        } catch (JsonMappingException  e) {
            System.err.println("The json configuration file is not valid. Please verify the file.");

        } catch (IOException e) {
            System.err.println("No configuration file available.");
        }
        return browsersList;

    }





}
