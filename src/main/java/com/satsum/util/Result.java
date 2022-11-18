//package com.satsum.util;
//
//import org.apache.tomcat.util.json.JSONParser;
//
//import java.io.*;
//import java.net.URL;
//import java.util.List;
//import java.util.stream.IntStream;
//
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//
//
//
//class Result {
//
//    /*
//     * Complete the 'apiResponseParser' function below.
//     *
//     * The function is expected to return an INTEGER_ARRAY.
//     * The function accepts following parameters:
//     *  1. STRING_ARRAY inputList
//     *  2. INTEGER size
//     */
//
//    public static <JSONObject> List<Integer> apiResponseParser(List<String> inputList, int size) {
//
//        System.out.println("inputList: "+ inputList + " size: "+size);
//
//        StringBuilder json = new StringBuilder();
//        URL url = new URL("https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users");
//        try (InputStream input = url.openStream()) {
//            InputStreamReader isr = new InputStreamReader(input);
//            BufferedReader reader = new BufferedReader(isr);
//
//            int c;
//            while ((c = reader.read()) != -1) {
//                json.append((char) c);
//            }
//            JSONParser parser = new JSONParser();
//            JSONObject json = (JSONObject) parser.parse(stringToParse);
//
//            Gson g = new Gson();
//            Student s = g.fromJson(jsonString, Student.class);
//
//
//            JSONObject json = new JSONObject(json);
//            System.out.println("jsonObject"+jsonObject);
//        }
//
//    }
//
//}
//
//class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int inputListCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<String> inputList = IntStream.range(0, inputListCount).mapToObj(i -> {
//                    try {
//                        return bufferedReader.readLine();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                })
//                .collect(toList());
//
//        int size = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> result = Result.apiResponseParser(inputList, size);
//
//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}
