package gsonExample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity {
    public static void main(String[] args){

        Gson gson = new Gson();
        /*
        Address address = new Address("Germany", "Berlin");
        List<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("Wife", 30));
        family.add(new FamilyMember("Daughter", 5));
        Employee employee = new Employee("John", 30, "john@gmail.com", address, family);
        String json = gson.toJson(family);
        */
        String json = "{\n" +
                "  \"address\": {\n" +
                "    \"city\": \"Berlin\",\n" +
                "    \"country\": \"Germany\"\n" +
                "  },\n" +
                "  \"age\": 30,\n" +
                "  \"family\": [\n" +
                "    {\n" +
                "      \"age\": 30,\n" +
                "      \"role\": \"Wife\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"age\": 5,\n" +
                "      \"role\": \"Daughter\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"first_name\": \"John\",\n" +
                "  \"mail\": \"john@gmail.com\"\n" +
                "}";

//        Type familyType = new TypeToken<ArrayList<FamilyMember>>() {}.getType();
//        ArrayList<FamilyMember> family = gson.fromJson(json, familyType);
        Employee employee = gson.fromJson(json, Employee.class);
    }


}
