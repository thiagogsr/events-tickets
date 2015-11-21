package eventstickets.policies;

import eventstickets.models.Role;
import eventstickets.models.User;

public class EventPolicy {
	public static boolean index(User user) {
		return user.getRole() == Role.MANAGER || user.getRole() == Role.ADMIN;
	}
	
	public static boolean report(User user) {
		return user.getRole() == Role.EMPLOYEE || user.getRole() == Role.ADMIN;
	}
	
	public static boolean register(User user) {
		return user.getRole() == Role.PARTICIPANT;
	}
}
