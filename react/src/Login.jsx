import React, {Component} from 'react';
import wallet from './dw.png';
import axios from 'axios';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button} from 'react-bootstrap';
import logueate from './logueate.svg'
import recibi from './Recibi_el_dinero.svg'
import transferi from './transferi.svg'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

export default class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            mail: '',
            password: '',
            error: '',
            success: '',
        };
    }


    handleClick(ev) {
        ev && ev.preventDefault();
        const {username, password} = this.state;
        let playLoad = {
            "email": username,
            "password": password
        };
        let endpoint = 'http://localhost:7000/login';
        axios.post(endpoint, playLoad)
            .then(response => this.props.history.push('/mainpage', {user: this.state.username}))
            .catch(() => this.setState({error: 'Bad username or password'}));
        console.log(username);
        console.log(password);
    }

    handleClick2() {
        this.props.history.push(`/register`);
    }

    changeEmail = (ev) => this.setState({username: ev.target.value, error: ''});

    render() {
        return (
            <div className="Login">
                <div className="Logi2">
                    <img src={wallet} className="App-Wallet-Logo" alt="walletLogo"/>
                    <h1>
                        Digital Wallet
                    </h1>
                    <form onSubmit={(ev) => this.handleClick(ev)}>
                        <div className="Usuario">
                            <input id="email" className={`form-control ${this.state.error && 'is-invalid'}`}
                                   value={this.state.username} type="email" placeholder={"Email address"}
                                   onChange={this.changeEmail}/>
                            {this.state.error && <small className="form-text text-danger">{this.state.error}</small>}
                        </div>
                        <div className="Contraseña">
                            <input id="password" className={`form-control ${this.state.error && 'is-invalid'}`}
                                   value={this.state.password} type={"password"} placeholder={"Password"}
                                   onChange={(ev) => this.setState({password: ev.target.value, error: ''})}/>
                        </div>
                    </form>
                    <div className={"SignIn"}>
                        <button type="submit" className="btn btn-primary" onClick={(ev) => this.handleClick(ev)}>Login
                        </button>
                    </div>
                    <div className={"Register1"}>
                        <Button type="button"  onClick={() => this.handleClick2()}>Register</Button>
                    </div>
                </div>
                <div className="columnas">
                    <Container className="container">
                        <Row className="row">
                            <Col className={"loguis"}>
                                <img className={"loguis"} src={logueate}></img>
                                <br></br>
                                <br></br>
                                <p>Ingresá con tu usuario y
                                    contraseña desde la
                                    web.</p>

                            </Col>
                            <Col className="loguis">
                                <img className={"loguis"} src={recibi}></img>
                                <br></br>
                                <br></br>
                                <p>Recibi dinero desde tu tarjeta de credito</p>
                            </Col>
                            <Col className={"loguis"}>
                                <img className="loguis" src={transferi}></img>
                                <br></br>
                                <br></br>
                                <p>Transferi o recibi dinero desde cualquier banco</p>
                            </Col>
                        </Row>
                    </Container>
                    <br></br>
                    <br></br>
                    <br></br>
                    <p>Copyright 2019 © Hynterfases</p>
                </div>
            </div>
        )
    }
}