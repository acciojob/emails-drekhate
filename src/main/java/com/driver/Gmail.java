package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String).
    // It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public class Mail {
        private Date date;
        private String senderId;
        private String message;

        public Mail(Date data, String senderId, String message) {
            this.date = data;
            this.senderId = senderId;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSenderId() {
            return senderId;
        }

        public String getMessage() {
            return message;
        }

    }

    public Gmail() {

    }

    Deque<Mail> Inbox;
    Deque<Mail> Trash;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        Inbox = new ArrayDeque<>();
        Trash = new ArrayDeque<>();

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if (Inbox.size() == inboxCapacity) {
            Mail currMail = Inbox.remove();
            Trash.add(currMail);
            Inbox.add(new Mail(date, sender, message));
        } else {
            Inbox.add(new Mail(date, sender, message));
        }

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        Stack<Mail> st = new Stack<>();
        for (Mail mail: Inbox) {
            if (message.equals(mail.getMessage())) {
                Inbox.remove();
                break;
            }
            st.push(Inbox.remove());
        }
        while (!st.isEmpty()) {
            Inbox.addFirst(st.pop());
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.isEmpty()) {
            return null;
        } else {
            Mail currEle = Inbox.peekLast();
            return currEle.getMessage();
        }
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.isEmpty()) {
            return null;
        } else {
            Mail currEle = Inbox.peekFirst();
            return currEle.getMessage();
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for (Mail mail: Inbox) {
            if (mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0) {
                count ++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
