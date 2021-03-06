package com.sematext.solr.redis.command;

import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCommands;
import java.util.Map;

public final class HMGet implements Command<JedisCommands> {
  private static final Logger log = LoggerFactory.getLogger(HMGet.class);

  @Override
  public Map<String, Float> execute(final JedisCommands client, final SolrParams params) {
    final String key = ParamUtil.assertGetStringByName(params, "key");
    final String[] fields = ParamUtil.getStringByPrefix(params, "field");

    log.debug("Fetching HMGET from Redis for key: {} ({})", key, fields);

    return ResultUtil.stringIteratorToMap(client.hmget(key, fields));
  }
}
