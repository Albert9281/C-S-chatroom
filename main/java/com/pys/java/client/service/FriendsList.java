package com.pys.java.client.service;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: yuisama
 * @Date: 2019-08-13 11:59
 * @Description:
 */
public class FriendsList {
    private JPanel friendsPanel;
    private JScrollPane frinedsList;
    private JButton createGroupBtn;
    private JScrollPane groupList;
    private JFrame frame;

    private String userName;
    private Set<String> users;
    private Connect2Server connect2Server;

    public FriendsList(String userName, Set<String> users,
                       Connect2Server connect2Server) {
        this.userName = userName;
        this.users = users;
        this.connect2Server = connect2Server;
        frame = new JFrame(userName);
        frame.setContentPane(friendsPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        loadUsers();
    }
    // 加载所有在线的用户信息
    public void loadUsers() {
        JLabel[] userLabels = new JLabel [users.size()];
        JPanel friends = new JPanel();
        friends.setLayout(new BoxLayout(friends,BoxLayout.Y_AXIS));
        // set遍历
        Iterator<String> iterator = users.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String userName = iterator.next();
            userLabels[i] = new JLabel(userName);
            friends.add(userLabels[i]);
            i++;
        }
        frinedsList.setViewportView(friends);
        // 设置滚动条垂直滚动
        frinedsList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friends.revalidate();
        frinedsList.revalidate();
    }
}
