package com.dc.ssj.config.shardingsphere;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Properties;

/**
 * 自定义hint分片算法
 *
 * @author duchao
 */
public class CustomHintShardingAlgorithm implements HintShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties properties) {

    }
}
