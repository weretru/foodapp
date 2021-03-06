/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Codename One through http://www.codenameone.com/ if you
 * need additional information or have any questions.
 */

package com.codename1.demos.ubereatsclone.views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.rad.models.Entity;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import static com.codename1.ui.CN.getDisplayWidth;
import static com.codename1.ui.util.Resources.getGlobalResources;

public class AccountView<T extends Entity> extends AbstractEntityView<T> {

    Node viewNode;

    public static final ActionNode.Category SIGN_IN = new ActionNode.Category();
    public static final ActionNode.Category SIGN_UP = new ActionNode.Category();


    public AccountView(T entity, Node viewNode) {
        super(entity);
        this.viewNode = viewNode;

        setLayout(new BorderLayout());
        setUIID("AccountCnt");

        Button signIn = new Button("Sign In", "SignInButton");
        signIn.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(SIGN_IN);
            if (action != null) {
                action.fireEvent(entity, AccountView.this);
            }
        });

        Button signUp = new Button("Sign Up", "SignUpButton");
        signUp.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(SIGN_UP);
            if (action != null) {
                action.fireEvent(entity, AccountView.this);
            }
        });

        Image grubLogo = getGlobalResources().getImage("grub-logo-full.png");
        ScaleImageLabel logoLabel = new ScaleImageLabel(grubLogo){
            @Override
            public Dimension getPreferredSize() {
                int width = getDisplayWidth() / 2;
                return new Dimension(getDisplayWidth() / 2 , (int)(width / 1.4));
            }
        };
        Label welcomeText = new Label("WELCOME TO GRUB APP", "AccountWelcomeText");

        Container topView = BoxLayout.encloseY(logoLabel, welcomeText);
        topView.setUIID("AccountTopView");
        add(BorderLayout.NORTH, topView);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(signUp, signIn));

    }

    @Override
    public void update() {

    }

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return null;
    }
}
