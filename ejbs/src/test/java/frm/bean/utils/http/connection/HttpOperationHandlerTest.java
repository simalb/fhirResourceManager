package frm.bean.utils.http.connection;


import frm.bean.persistence.entity.PatientEntity;
import frm.bean.persistence.utils.ConverterUtility;
import frm.bean.utils.http.connection.exception.HttpURLConnectionFailException;
import frm.bean.utils.json.objects.Patient;
import frm.bean.utils.json.utils.JsonManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static frm.bean.FhirResourceManagerHandler.TEST_URI;


/**
 * The type Json handler tester.
 */
@RunWith(MockitoJUnitRunner.class)
public class HttpOperationHandlerTest {

    /*@Inject
    private HttpOperationHandler httpOperationHandler;*/

    /**
     * The Json handler.
     */
    //JsonHandler jsonHandler = new JsonHandler();

    //HttpOperationHandler httpOperationHandler = new HttpOperationHandler();

    /**
     * The Get interface list response.
     */
    /*String getInterfaceListResponse =
            "{" +
            "\n   \"output\" : {" +
            "\n     \"network-ref\" : \"mini-link-topo\"," +
            "\n     \"result-ok\" : \"true\"," +
            "\n     \"interfaces\" : {" +
            "\n        \"interface-list\" : [" +
            "\n           \"mini-link-1:1/1/1\"," +
            "\n           \"mini-link-2:2/3/3\" "+
            "\n       ]" +
            "\n     }" +
            "\n   }" +
            "\n}";*/

    /**
     * The Set interface monitored rate response.
     */
    /*String setInterfaceMonitoredRateResponse =
            "{" +
            "\n   \"output\" : {" +
            "\n     \"time-interval\" : \"10\"," +
            "\n     \"tx-monitored-rate\" : \"100000\"," +
            "\n     \"result-ok\" : \"true\"" +
            "\n   }" +
            "\n}";*/


    //final static private String POSTJSON = "{ \"output\" : {} }";
    //final static private String POSTJSONINTERFACES = "\"interfaces\" : {} ";

    /*public String buildCreateStreamPostRequest(final String addressPath,
                                               final String relativePath, final String datastoreType, final String scopeType) {
        String jsonString = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root_node = objectMapper.readValue(POSTJSON, JsonNode.class);
            if (root_node == null) {
                return null;
            }
            JsonNode node = root_node.path("output");
            if (node == null) {
                return null;
            }
            ((ObjectNode) node).put("path", relativePath);
            ((ObjectNode) node).put("sal-remote-augment:datastore", datastoreType);
            ((ObjectNode) node).put("sal-remote-augment:scope", scopeType);
            jsonString = objectMapper.writeValueAsString(root_node);
        } catch (final Exception e) {
            LOG.error("JsonHandler.buildCreateStreamPostRequest exception ", e);
        }

        return jsonString;
    }*/


    @Test
    public void testGet() {
        System.out.println("*** Starting testGet");
        try {
            HttpOperationHandler httpOperationHandler = new HttpOperationHandler();
            ResultHandler resultHandler = httpOperationHandler.get(TEST_URI);

            Patient patient = JsonManager.getPatientFromJson(resultHandler.getResultMessage());
            PatientEntity patientEntity = ConverterUtility.getCompletePatientEntity(patient, TEST_URI);

            System.out.println("*** Ending testGet");

        } catch (HttpURLConnectionFailException e) {
            e.printStackTrace();
        }
    }

    
    //@Test
   /*public void getInterfaceListResponse() {
        System.out.println("*** start getInterfaceListResponse ***");
   

        try {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootNode = mapper.createObjectNode();
            JsonNode marksNode = mapper.createArrayNode();
            JsonNode root_node = mapper.readValue(POSTJSON, JsonNode.class);
            if (root_node != null) {

                JsonNode node = root_node.path("output");
                if (node != null) {

                    ((ArrayNode) marksNode).add("mini-link-1:1/1/1");
                    ((ArrayNode) marksNode).add("mini-link-2:2/3/3");
                    ((ObjectNode) node).put("interface-list", marksNode);
                    ((ObjectNode) node).put("network-ref", "simona");
                    ((ObjectNode) node).put("result-ok", true);

                    String jsonString = mapper.writeValueAsString(root_node);
                    System.out.println(jsonString);

                    JsonNode internal_root_node  = mapper.readValue(POSTJSONINTERFACES, JsonNode.class);
                    JsonNode node2 = internal_root_node.path("interfaces");

                    ((ObjectNode) node2).put("interface-list", marksNode);
                    String jsonString2 = mapper.writeValueAsString(internal_root_node);
                    System.out.println(jsonString2);

                    //((ObjectNode) node).put(node2.toString());
                    //map.put("1", node2);
                    //((ObjectNode) node).putAll(map);
                }
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
           }


   }*/

    /**
     * Test read and convert json file in string.
     */
    /*@Test
    public void testReadAndConvertJSONFileInString() {
        System.out.println("*** start testReadAndConvertJSONFileInString ***");
        try {
            String jsonInputString = jsonHandler.readAndConvertJSONFileInString(NETWORK_TOPOLOGY_JSON_FILE_NAME_FOR_TEST);
            System.out.println(jsonInputString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Test compile set interface rate data template.
     */
    /*@Test
	public void testCompileSetInterfaceRateDataTemplate() {
        System.out.println("*** start testCompileSetInterfaceRateDataTemplate ***");

        try {
            String jsonInputString = jsonHandler.compileSetInterfaceCapacityTemplate("mini-link-topo", "mini-link-1:1/3/5", 10000000L);
            System.out.println(jsonInputString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Test compile get interface rate data template.
     */
    /*@Test
    public void testCompileGetInterfaceMonitoredDataTemplate() {
        System.out.println("*** start testCompileGetInterfaceMonitoredDataTemplate ***");

        try {
            String jsonInputString = jsonHandler.compileGetInterfaceMonitoredDataTemplate("mini-link-topo", "mini-link-1:1/3/5");
            System.out.println(jsonInputString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Test compile enable disable interface rate monitoring.
     */
   /* @Test
    public void testCompileEnableDisableInterfaceRateMonitoring() {
        System.out.println("*** start testCompileEnableDisableInterfaceRateMonitoring ***");

        try {
            String jsonInputString = jsonHandler.compileEnableDisableInterfaceRateMonitoring(
                    "mini-link-topo", "mini-link-1:1/3/5", "1000", "true", "2600");
            System.out.println(jsonInputString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Test is result ok.
     */
    /*@Test
    public void testIsResultOk() {
        System.out.println("*** start testIsResultOk ***");

        System.out.println("Test on getInterfaceListResponse ***");
        System.out.println(getInterfaceListResponse);
        assertTrue(jsonHandler.isResultOk(getInterfaceListResponse));

        System.out.println("Test on setInterfaceMonitoredRateResponse ***");
        System.out.println(setInterfaceMonitoredRateResponse);
        assertTrue(jsonHandler.isResultOk(setInterfaceMonitoredRateResponse));


    }*/

    /**
     * Test parse get network id.
     */
    /*@Test
    public void testParseGetNetworkId() {
        System.out.println("*** start testParseGetNetworkId ***");
        System.out.println("Test on parseGetNetworkId ***");

        String networkId = jsonHandler.parseGetNetworkId(NETWORK_TOPOLOGY_JSON_FILE_NAME_FOR_TEST);
        System.out.println("Result: networkId = " + networkId);
        assertEquals("mini-link-topo", networkId);

    }*/

    /**
     * Test parse get interface list.
     */
    /*@Test
    public void testParseGetInterfaceList() {
        System.out.println("*** start testParseGetInterfaceList ***");
        System.out.println(getInterfaceListResponse);

        List<InterfaceData> interfaceDataList = jsonHandler.parseGetInterfaceDataList(getInterfaceListResponse);
        System.out.println("Final result: " + interfaceDataList.toString());
    }*/
}
