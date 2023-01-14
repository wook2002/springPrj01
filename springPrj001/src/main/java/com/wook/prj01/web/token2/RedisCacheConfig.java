package com.wook.prj01.web.token2;

// https://charliecharlie.tistory.com/397
/*
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	protected final static Logger log = LoggerFactory.getLogger(RedisCacheConfig.class);
	private volatile JedisConnectionFactory mJedisConnectionFactory;
	private volatile RedisTemplate<String, String> mRedisTemplate;
	private volatile RedisCacheManager mRedisCacheManager;

	public RedisCacheConfig() {
		super();
	}

	public RedisCacheConfig(JedisConnectionFactory mJedisConnectionFactory,
			RedisTemplate<String, String> mRedisTemplate, RedisCacheManager mRedisCacheManager) {
		super();
		this.mJedisConnectionFactory = mJedisConnectionFactory;
		this.mRedisTemplate = mRedisTemplate;
		this.mRedisCacheManager = mRedisCacheManager;
	}

	public JedisConnectionFactory redisConnectionFactory() {
		return mJedisConnectionFactory;
	}

	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		return mRedisTemplate;
	}

	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		return mRedisCacheManager;
	}

	// 캐시의 key를 생성(중복X)
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {

			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
}
*/