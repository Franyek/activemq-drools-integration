package hello;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kie.api.KieBase;
import org.kie.api.definition.type.FactType;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class MessageConsumer {

    private static Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private KieContainer kc;

    static class TypeName {
        public String packageName;
        public String typeName;

        @Override
        public String toString() {
            return packageName + "." + typeName;
        }
    }

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(String message) throws JSONException {
        JSONArray jsonArray = new JSONArray(message);
        System.out.println(message);

        List<Object> facts = doExecute(jsonArray);
        for (Object o : facts){
            System.out.println(o);
        }
    }

    private List<Object> doExecute(JSONArray factNodes) throws JSONException {

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

            TypeName typeName = extractTypeName(typeNode);
            FactType factType = kb.getFactType(typeName.packageName, typeName.typeName);
            checkNotNull(factType, "Unknown input type %s", typeName);

            Class<?> factClass = factType.getFactClass();

            Gson gson = new Gson();
            Object inputFact = gson.fromJson(node.toString(), factClass);

            facts.add(inputFact);
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
