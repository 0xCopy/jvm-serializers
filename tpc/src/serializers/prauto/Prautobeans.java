package serializers.prauto;

import serializers.*;
import serializers.wobly.simple.WoblySimpleUtils;

public class Prautobeans {
	public static void register(TestGroups groups)
	{
		groups.media.add(new prau.WoblyTransformer(), new WoblySimpleUtils.WoblySerializer(),
                new SerFeatures(
                        SerFormat.BINARY,
                        SerGraph.FLAT_TREE,
                        SerClass.CLASSES_KNOWN,
                        "proto, interfaces."
                )
        );

	}
	
}
