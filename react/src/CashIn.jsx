import React from 'react';
import axios from 'axios';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {withRouter} from "react-router";
import NavBarDw from "./NavBarDW"
import {Alert, Button} from "react-bootstrap";


class CashIn extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            cvu: '',
            amount: '',
            cardNumber: '',
            fullName: '',
            securityCode: '',
            endDate: '',
            error: ''
        };
    }


    componentDidMount() {
        axios.get(`http://localhost:7000/cvu/${this.props.location.state.user}`)
            .then(response => {
                this.setState({cvu: response.data});
            })
            .catch(error => console.log("**** ERROR ****", error.toJSON()));
        let actualDate = new Date().toLocaleDateString();
        this.setState({endDate: actualDate})
    }

    handleClickCashIn() {
        const {cvu, amount, cardNumber, fullName, endDate, securityCode} = this.state;
        let playLoad = {
            "fromCVU": cvu,
            "amount": amount,
            "cardNumber": cardNumber,
            "fullName": fullName,
            "endDate": endDate,
            "securityCode": securityCode
        };

        if (this.validarDatos(playLoad)) {
            let endpoint = 'http://localhost:7000/cashIn';
            axios.post(endpoint, playLoad)
                .then(response => this.props.history.push('/mainpage', {user: this.props.location.state.user}))
                .catch((error) => this.setState({error: error.response.data.message}))
        }

    }

    validarDatos(playLoad) {

        if (this.isEmpty(playLoad.cvu) && this.isEmpty(playLoad.amount) && this.isEmpty(playLoad.cardNumber) && this.isEmpty(playLoad.securityCode)) {
            this.setState({error: "Por favor complete todos los campos"});
            return false
        }

        if (isNaN(playLoad.amount) || parseInt(playLoad.amount) < 0) {
            this.setState({error: "Por favor ingrese un monto válido"});
            return false
        }

        if (isNaN(playLoad.cardNumber)) {
            this.setState({error: "Por favor ingrese una tarjeta válida"});
            return false
        }

        return true;
    }


    isEmpty(value) {
        return (typeof value === 'undefined' || value === null || value === '');
    }

    handleClickBack() {
        this.props.history.push(`/mainpage`, {user: this.props.location.state.user});
    }

    render() {
        return (
            <div className={"MainPageTop"}>
                <NavBarDw/>
                <div className={"MainPageMiddle"}>
                    <form>
                        <div className="container flex-center">
                            <div className={"cashin"}>
                                <h3 className="p30">Cash In</h3>
                                <div className="FormContainer">
                                    <div className="row">
                                        <div className="col-sm-6 mb-15">
                                            <label> CVU </label>
                                            <input value={this.state.cvu}
                                                   onChange={event => this.setState({cvu: event.target.value})}
                                                   type={"text"}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-6 mb-15">
                                            <label> Amount </label>
                                            <input value={this.state.amount}
                                                   onChange={event => this.setState({amount: event.target.value})}
                                                   type={"numeric"}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-12 mb-15">
                                            <label> Card Number </label>
                                            <input value={this.state.cardNumber}
                                                   onChange={event => this.setState({cardNumber: event.target.value})}
                                                   type={"numeric"}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-12 mb-15">
                                            <label> Full Name </label>
                                            <input value={this.state.fullName}
                                                   onChange={event => this.setState({fullName: event.target.value})}
                                                   type={"text"}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-6 mb-15">
                                            <label> Security Code </label>
                                            <input value={this.state.securityCode}
                                                   onChange={event => this.setState({securityCode: event.target.value})}
                                                   type={"password"}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-6 mb-15">
                                            <label> Valid Thru </label>
                                            <input value={this.state.endDate}
                                                   onChange={event => this.setState({endDate: event.target.value})}
                                                   type={"text"} disabled={true}
                                                   className="form-control"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div className={"cashINWarning"}>
                                    {this.state.error &&
                                    <Alert variant={"danger"}>
                                        {this.state.error}
                                    </Alert>
                                    }
                                </div>
                                <div className="mt-2">
                                    <Button onClick={() => this.handleClickCashIn()}>Cash In</Button>
                                    <Button variant="dark" className={"ml-1rem"}
                                            onClick={() => this.handleClickBack()}>Cancel</Button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        )
    }
}

export default withRouter(CashIn)