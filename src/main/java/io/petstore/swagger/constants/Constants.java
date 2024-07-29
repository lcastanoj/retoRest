package io.petstore.swagger.constants;

import com.google.gson.Gson;
import io.petstore.swagger.questions.BuildPutBodyAndGetResponseUser;
import io.petstore.swagger.utils.ExcelReader;
import io.petstore.swagger.utils.JsonReader;
import net.serenitybdd.screenplay.Actor;
import org.json.JSONObject;
import io.petstore.swagger.models.PostUserModel;

import java.util.Map;

public class Constants {

    public static String getExpectedJsonResponseUser(String key, Integer index) {
        String expectedJson = JsonReader.readJson("src/test/resources/dataTest/GetResponse.json");
        JSONObject expectedJsonObject = new JSONObject(expectedJson);
        return String.valueOf(expectedJsonObject.getJSONArray(key).getJSONObject(index));
    }

    public static String infoResponse(Actor actor, Integer index, String page){
        Gson gson = new Gson();
        Map<String, String> excelData;
        if (page.equals("Users") || page.equals("UsersPut")) {
            excelData = constantsExcelUser(index, page);
            PostUserModel userInfoResponse = actor.asksFor(BuildPutBodyAndGetResponseUser.was(excelData.get("id"), excelData.get("username"), excelData.get("firstName"), excelData.get("lastName"), excelData.get("email"), excelData.get("password"), "+57 " + excelData.get("phone"), excelData.get("userStatus")));
            return gson.toJson(userInfoResponse);
        }

        else {
            return "Error";
        }
    }

    public static Map<String, String> constantsExcelUser(Integer index, String page) {
        return ExcelReader.parseXlsx("src/test/resources/dataTest/excelDataUser.xlsx", index, page);
    }
}
