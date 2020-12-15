import React from 'react';
import axios from 'axios';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {withRouter} from "react-router";
import NavBarDw from "./NavBarDW"
import {Alert, Button} from "react-bootstrap";


class Transfer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            fromCVU: '',
            toCVU: '',
            amount: '',
            error: ''
        };
    }


    componentDidMount() {
        axios.get(`http://localhost:7000/cvu/${this.props.location.state.user}`)
            .then(response => {
                this.setState({fromCVU: response.data});
                console.log("GET CVU: ", response)
            })
            .catch(error => console.log("**** ERROR ****", error.toJSON()))
    }

    handleClick() {
        const {fromCVU, toCVU, amount} = this.state;
        let playLoad = {
            "fromCVU": fromCVU,
            "toCVU": toCVU,
            "amount": amount
        };
        let endpoint = 'http://localhost:7000/transfer';
        if (!this.isEmpty(fromCVU) && !this.isEmpty(toCVU) && !this.isEmpty(amount)) {
            axios.post(endpoint, playLoad)
                .then(() => this.props.history.push('/mainpage', {user: this.props.location.state.user}))
                .catch((error) => this.setState({error: error.response.data.message}))
        } else {
            this.setState({error: "Por Favor Complete Todos Los Campos"})
        }

    }

    isEmpty(value) {
        return (typeof value == 'undefined' || value == null || value == '');
    }

    handleClick2() {
        this.props.history.push(`/mainpage`, {user: this.props.location.state.user});
    }

    render() {
        return (
            <div className={"MainPageTop"}>
                <NavBarDw/>
                <div className={"MainPageMiddle"}>
                    <form>
                        <div className="container flex-center">
                            <div className={"Transfer"}>
                                <h3 className="p30">Realizar Transferencia</h3>
                                <div className="FormContainer">
                                    <div className="row">
                                        <div className="col-sm-12 mb-30">
                                            <label> Mi CVU </label>
                                            <input value={this.state.fromCVU}
                                                   onChange={event => this.setState({fromCVU: event.target.value})}
                                                   type="text" disabled={true}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-12 mb-30">
                                            <label> CVU Destinatario </label>
                                            <input value={this.state.toCVU}
                                                   onChange={event => this.setState({toCVU: event.target.value})}
                                                   type={"text"}
                                                   className="form-control"
                                            />
                                        </div>
                                        <div className="col-sm-12 mb-15">
                                            <label> Monto </label>
                                            <input value={this.state.amount}
                                                   onChange={event => this.setState({amount: event.target.value})}
                                                   type={"number"}
                                                   className="form-control"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div className={"transferWarning"}>
                                    {this.state.error &&
                                        <Alert variant="danger">{this.state.error}</Alert>
                                    }
                                </div>
                                <div className="mt-4">
                                    <Button onClick={() => this.handleClick()}>Confirmar</Button>
                                    <Button variant="dark" className={"ml-1rem"}
                                            onClick={() => this.handleClick2()}>Cancelar</Button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default withRouter(Transfer);