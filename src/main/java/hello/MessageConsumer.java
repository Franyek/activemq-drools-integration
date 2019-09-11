package hello;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kie.api.KieBase;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class MessageConsumer {

    private static Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    public static KieContainer kc;

    static class TypeName {
        public String packageName;
        public String typeName;

        @Override
        public String toString() {
            return packageName + "." + typeName;
        }
    }

    @JmsListener(destination = "${activemq.queue.name}", containerFactory = "myFactory")
    public void receive(String message) throws JSONException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        log.info(message);
        JSONArray jsonArray = new JSONArray(message);

        List<Object> facts = doExecute(jsonArray);
        for (Object o : facts){
            log.info(o.toString());
        }
    }

    private List<Object> doExecute(JSONArray factNodes) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException, JSONException {

        StatelessKieSession ksession = kc.newStatelessKieSession();

        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugRuleRuntimeEventListener());

        KieBase kb = kc.getKieBase();


        List<Object> facts = new ArrayList<>();
        for (int i = 0; i < factNodes.length(); i++) {
            JSONObject node = factNodes.getJSONObject(i);

            String typeNode = node.getString("_type");
            checkNotNull(typeNode, "Missing _type information");
            node.remove("_type");

//            TypeName typeName = extractTypeName(typeNode);
//            FactType factType = kb.getFactType(typeName.packageName, typeName.typeName);
//            checkNotNull(factType, "Unknown input type %s", typeName);
//
//            Class<?> factClass = factType.getFactClass();
//
//            Gson gson = new Gson();
//            Object inputFact = gson.fromJson(node.toString(), factClass);

            HashMap<String,Object> result = new Gson().fromJson(node.toString(), HashMap.class);
            Class inputClass = Class.forName(typeNode);
            Constructor<?> cons = inputClass.getConstructor(HashMap.class);
            Object o = cons.newInstance(result);
            facts.add(o);
        }

        ksession.execute(facts);
        return facts;
    }

    private TypeName extractTypeName(String type) {
        int i = type.lastIndexOf(".");
        String packageName = type.substring(0, i);
        String typeName = type.substring(i + 1);
        TypeName typeName_ = new TypeName();
        typeName_.packageName = packageName;
        typeName_.typeName = typeName;
        return typeName_;
    }
}
