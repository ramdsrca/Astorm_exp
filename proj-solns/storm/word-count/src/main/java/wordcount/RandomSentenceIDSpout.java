package wordcount;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;


public class RandomSentenceIDSpout extends BaseRichSpout {

    public static final String SPOUT_NAME = "generator";
    public static final String EMIT_SENTENCE = "sentence";
    public static final String EMIT_BOGUS_KEY = "BOGUS";

    private static final String[] SENTENCES =
            new String[] { "the cow jumped over the moon",
                    "an apple a day keeps the doctor away",
                    "four score and seven years ago",
                    "snow white and the seven dwarfs",
                    "i am at two with nature"};

    private SpoutOutputCollector collector;
    private Random random;

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
        this.random = new Random();
    }

    public void nextTuple() {
        Utils.sleep(15000);
        String sentence = SENTENCES[this.random.nextInt(SENTENCES.length)];
        System.out.println("\n******* Random Sentence Spout ***** " + sentence + " ******\n");
        this.collector.emit(new Values(EMIT_BOGUS_KEY, sentence));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(EMIT_BOGUS_KEY, EMIT_SENTENCE));

    }

}
