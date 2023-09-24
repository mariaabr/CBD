package redis.lab01_3.b;

import java.util.*;
import redis.clients.jedis.Jedis;
 
public class SimplePost {
 
	private Jedis jedis;
	public static String USERS = "users"; // Key set for users' name
	public static String USERS_list = "users_list"; // Key set for users' name
	
	public SimplePost() {
		this.jedis = new Jedis();
	}
	
	// read and write over a set

	public void saveUser(String username) {
		jedis.sadd(USERS, username);
	}
	public Set<String> getUser() {
		return jedis.smembers(USERS);
	}
	
	public Set<String> getAllKeys() {
		return jedis.keys("*");
	}
	
	// read and write over a list

	public void saveUserList(String username) {
		jedis.lrem(USERS_list, 1, username);
		jedis.rpush(USERS_list, username);
	}

	public List<String> getUserList() {
		// System.out.println("hi");
		return jedis.lrange(USERS_list, 0, -1);
	}

	// read and write over a hashmap

	public void saveUserHashMap(String username, Map<String, String> map){
		jedis.hmset(username, map);
	}

	public Map<String, String> getUserHashMap(String username) {
		return jedis.hgetAll(username);
	}

	public static void main(String[] args) {
		SimplePost board = new SimplePost();
		// set some users
		String[] users = { "Ana", "Pedro", "Maria", "Luis" };
		
		System.out.println("AllKeys: ");
		board.getAllKeys().stream().forEach(System.out::println);

		System.out.println();
		System.out.println("Set: ");

		for (String user: users) 
			board.saveUser(user);

		board.getUser().stream().forEach(System.out::println);

		System.out.println("---------------");
		System.out.println();
		System.out.println("List: ");

		for (String user: users){ 
			board.saveUserList(user);
		}
		
		board.getUserList().stream().forEach(System.out::println);

		System.out.println("---------------");
		System.out.println();
		System.out.println("HashMap: ");

		HashMap<String, String> user_info = new HashMap<>();

		// preencher com info, hashmap do tipo k = user e v = info
		user_info.put("Ana", "18");
		user_info.put("Pedro", "19");
		user_info.put("Maria", "23");
		user_info.put("Luís", "20");

		board.saveUserHashMap("idade", user_info);
		System.out.println(board.getUserHashMap("idade").entrySet());

		for (Map.Entry<String, String> info : board.getUserHashMap("idade").entrySet()) {
			System.out.println("Idade: " + info.getKey() + " " + info.getValue());
		}
	}
}