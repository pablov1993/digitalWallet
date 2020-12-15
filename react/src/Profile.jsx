import React from 'react';
import axios from 'axios';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {withRouter} from "react-router";
import NavBarDw from "./NavBarDW"
import FormControl from "react-bootstrap/FormControl";
import Button from "react-bootstrap/Button";


class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            fistName: '',
            lastName: '',
            email: '',
            cvu: '',
            balance: '',
        };
    }


    componentDidMount() {
        axios.get(`http://localhost:7000/user/${this.props.location.state.user}`)
            .then(response => this.setState({
                firstName: response.data.firstName,
                lastName: response.data.lastName,
                email: response.data.email,
                cvu: response.data.cvu,
                balance: response.data.balance.toFixed(2)
            }))
            .catch(error => console.log("**** ERROR ****", error.toJSON()))
    }


    handleClickBack() {
        this.props.history.push(`/mainpage`, {user: this.props.location.state.user});
    }


    render() {
        return (
            <div className={"MainPageTop"}>
                <NavBarDw/>
                <div className={"MainPageMiddle"}>
                    <form class={"menu"}>
                        <div className="container">
                            <div className="Profile">
                                <h1> User Profile</h1>
                                <div className="col-md-4 mb-3">
                                    <h2></h2>
                                    <label htmlFor="validate">Nombre</label>
                                    <FormControl type="text" value={this.state.firstName}
                                                 className="form-control" id="validate" placeholder="firstName"
                                    />
                                </div>
                                <div className="col-md-4 mb-3">
                                    <h2></h2>
                                    <label htmlFor="validate">Apellido</label>
                                    <FormControl type="text" value={this.state.lastName} className="form-control"
                                                 id="validate" placeholder="lastName"
                                    />
                                </div>
                                <div className="col-md-4 mb-3">
                                    <label htmlFor="validate">Email</label>
                                    <FormControl type="text" value={this.state.email} className="form-control"
                                                 id="validate"
                                                 placeholder="email"
                                    />
                                </div>
                                <div className="col-md-4 mb-3">
                                    <label htmlFor="validate">CVU</label>
                                    <FormControl type="text" value={this.state.cvu} className="form-control"
                                                 id="validate"
                                                 placeholder="cvu"
                                    />
                                </div>
                                <div className="col-md-4 mb-3">
                                    <label htmlFor="validate">Balance</label>
                                    <FormControl type="numeric" value={this.state.balance} className="form-control"
                                                 id="validate" placeholder="balance"
                                    />
                                </div>
                                <div className={"cashInWarning"}>
                                    {this.state.error && <small className="form-text text-danger">{this.state.error}</small>}
                                </div>
                                <br>
                                </br>
                                <Button onClick={() => this.handleClickBack()}>Go Back</Button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default withRouter(Profile);