package org.javax.usage;

import java.util.Arrays;

/**
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/4/8
 * Time: 10:20
 */
public class HashCodeUsage {


    static class User {
        private String userName;
        private Long userId;
        private String[] address;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }


        @Override
        public int hashCode() {
            return userId.hashCode() * userName.hashCode() * Arrays.hashCode(address);
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof User)) {
                return false;
            }
            User user = (User) obj;

            return userName.equals(user.getUserName()) && userId == user.getUserId()  && Arrays.equals(this.address,user.getAddress());
        }

        public void setAddress(String[] address) {
            this.address = address;
        }

        public String[] getAddress() {
            return address;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getUserId() {
            return userId;
        }
    }


    public static void main(String[] args) {

        String[] strArr=new String[2];
        String[] strArr2=new String[2];


        User user1 = new User();
        user1.setUserId(100L);
        user1.setUserName("krisjin");
        user1.setAddress(strArr);



//        strArr[0]="test";
        User user2 = new User();
        user2.setUserId(100L);
        user2.setUserName("krisjin");
        user2.setAddress(strArr2);

        System.out.println("User1 equals User2 :" + user1.equals(user2));
        System.out.println("User1 hashcode :" + user1.hashCode());
        System.out.println("User2 hashcode :" +user2.hashCode());

    }
}
