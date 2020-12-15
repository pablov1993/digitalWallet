import React from 'react';
import {withRouter} from 'react-router';
import wallet from './dw.png';
import axios from 'axios';
import {Navbar, Button} from 'react-bootstrap';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from "react-router-dom";


class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            idCard: '',
            email: '',
            password: '',
            error: '',
            success: ''
        };
    }

    handleClick(ev) {
        ev && ev.preventDefault();
        const {idCard, firstName, lastName, email, password} = this.state;
        let params = {idCard, firstName, lastName, email, password};

        let endpoint = 'http://localhost:7000/register';
        if (this.isEmpty(idCard) || this.isEmpty(firstName) || this.isEmpty(lastName) || this.isEmpty(email) || this.isEmpty(password)) {
            this.setState({error: 'Por favor, complete todos los campos.'})
        } else {
            if (this.validateEmail(email) && this.validateDNI(idCard)) {
                axios.post(endpoint, params)
                    .then(response => this.props.history.push('/', this.setState({success: response.data.message})))
                    .catch((error) => this.setState({error: error.response.data.message}));
            }
        }
    }


    isEmpty(value) {
        return (typeof value == 'undefined' || value == null || value == '');
    }

    validateEmail(email) {
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
            return true;
        }
        this.setState({error: 'Por favor, introduzca un email valido.'});
        return false;
    }

    validateDNI(idCard) {
        if (idCard.toString().length === 8) {
            return true;
        } else {
            this.setState({error: 'Por favor, introduzca un DNI valido.'});
            return false;
        }
    }

    handleClick2() {
        this.props.history.push(`/`);
    }

    render() {
        return (
            <div className={"Register"}>
                <div className={"MainBar"}>
                    <Navbar bg="light   " expand="lg">
                        <img
                            src={wallet}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                        />
                        <Link to="/">Digital Wallet</Link>
                        <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                    </Navbar>
                </div>
                <br></br>
                <br></br>
                <h3>Por favor, complete los campos obligatorios</h3>
                <form class={"menu"} onSubmit={(ev) => this.handleClick(ev)}>
                    <div class="wrapper">
                        <div class="input-group">
                            <label/> Nombre: <label/>
                            <input type={"nombre"} value={this.state.firstName}
                                   className={`form-control ${this.state.error && 'is-invalid'}`}
                                   onChange={(ev) => this.setState({firstName: ev.target.value, error: ''})}/>

                        </div>
                        <div class="input-group">
                            <label/> Apellido: <label/>
                            <input type={"apellido"} value={this.state.lastName}
                                   className={`form-control ${this.state.error && 'is-invalid'}`}
                                   onChange={(ev) => this.setState({lastName: ev.target.value, error: ''})}/>

                        </div>
                        <div class="input-group">
                            <label/> DNI: <label/>
                            <input type={"numeric"} value={this.state.idCard}
                                   className={`form-control ${this.state.error && 'is-invalid'}`}
                                   onChange={(ev) => this.setState({idCard: ev.target.value, error: ''})}/>

                        </div>
                        <div class="input-group">
                            <label/> Correo Electronico: <label/>
                            <input type={"email"} value={this.state.email}
                                   className={`form-control ${this.state.error && 'is-invalid'}`}
                                   onChange={(ev) => this.setState({email: ev.target.value, error: ''})}/>

                        </div>
                        <div class="input-group">
                            <label/> Contraseña: <label/>
                            <input type={"password"} value={this.state.password}
                                   className={`form-control ${this.state.error && 'is-invalid'}`}
                                   onChange={(ev) => this.setState({password: ev.target.value, error: ''})}/>
                        </div>
                        <div className={"registerWarning"}>
                            {this.state.error && <small className="form-text text-danger">{this.state.error}</small>}
                        </div>
                    </div>
                    <div className="buttons">
                        <Button onClick={() => this.handleClick()}>Registrar</Button>
                        <Button className="ml-1rem" onClick={() => this.handleClick2()}>Cancelar</Button>
                    </div>
                </form>
            </div>
        )
    }
}

export default withRouter(Register);