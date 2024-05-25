import { Layout, Space } from "antd";
import { Content, Footer, Header } from "antd/es/layout/layout";
import NavBar from "./navbar";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getMe } from "../service/user";
import { UserContext } from "../lib/context";

export function BasicLayout({ children }) {
    return (
        <Layout className="basic-layout">
            <Header className="header"><NavBar user={null} /></Header>
            <Content>
                {children}
            </Content>
            <Footer className="footer">

            </Footer>
        </Layout>
    )
}

export function PrivateLayout({ children }) {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    const checkLogin = async () => {
        let me = await getMe();
        if (!me) {
            navigate("/login");
        } else {
            setUser(me);
        }
    }

    useEffect(() => {
        checkLogin();
    }, []);

    return (
        <Layout className="basic-layout">
            <Header className="header"><NavBar user={user} /></Header>
            <Content>
                <UserContext.Provider value={user}>{user && children}</UserContext.Provider>
            </Content>
            <Footer className="footer">
            </Footer>
        </Layout>
    )
}