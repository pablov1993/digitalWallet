import React from 'react';
import './App.css';
import {Link, withRouter} from "react-router-dom";
import {Nav, Navbar, NavDropdown} from "react-bootstrap";
import DropdownButton from "react-bootstrap/DropdownButton";


class NavBarDW extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Navbar bg="dark" variant="dark">
                    <Link className="nav-link"
                          to={{pathname: "/mainpage", state: {user: this.props.location.state.user}}}>Digital
                        Wallet</Link>
                    <Nav className="mr-auto">
                        <Link className="nav-link" to={{
                            pathname: "/transfer",
                            state: {user: this.props.location.state.user}
                        }}>Transfer</Link>
                        <Link className="nav-link"
                              to={{pathname: "/cashIn", state: {user: this.props.location.state.user}}}>Cash In</Link>
                    </Nav>
                    <Nav>
                        <DropdownButton title="Account" id="nav-dropdown">
                            <Link to={{pathname: "/profile", state: {user: this.props.location.state.user}}}> User
                                Profile</Link>
                            <NavDropdown.Divider/>
                            <Link to="/">Logout</Link>
                        </DropdownButton>
                    </Nav>
                </Navbar>
            </div>
        );
    }

}

export default withRouter(NavBarDW);