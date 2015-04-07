package org.javax.usage;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/7
 * Time: 20:39
 */
public class EqualsUsage {


    static class User {

        protected String userName;
        protected String userId;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof User)) {
                return false;
            }
            User user = (User) obj;
            return userName.equals(user.getUserName()) && userId.equals(user.getUserId());
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static void main(String[] args) {

        User user1 = new User();
        user1.setUserId("1234");
        user1.setUserName("krisjin");

        User user2 = new User();
        user2.setUserId("123");
        user2.setUserName("krisjin");

        System.out.println("User1 equals User2: " + user1.equals(null));

    }

}
