import React from 'react';
import {withRouter} from 'react-router';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';
import axios from 'axios';
import Transaction from './Transaction.jsx';
import Table from "react-bootstrap/Table";
import NavBarDw from "./NavBarDW"

class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            mail: '',
            password: '',
            transactions: []
        };
    }

    componentDidMount() {
        axios.get(`http://localhost:7000/cvu/${this.props.location.state.user}`)
            .then(response => {
                axios.get(`http://localhost:7000/transaccions/${response.data}`)
                    .then(data => {
                            console.log("*********---", data);
                            this.setState({transactions: data.data})
                        }
                    ).catch(error => console.log("INTERNAL error: ", error.toJSON()))
            })
            .catch(error => console.log("**** ERROR ****", error.toJSON()))
    }


    renderTransactions() {
        const {transactions} = this.state;
        if (transactions.length === 0) {
            return <div>No Hay Transacciones Disponibles Para Mostrar</div>
        }
        return (
            <div className="listaTransacciones">
                <Table striped bordered hover variant="dark">
                    <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Descripcion</th>
                        <th>Monto</th>
                    </tr>
                    </thead>
                    <tbody>
                    {transactions.map(tran => <Transaction data={tran}/>)}
                    </tbody>
                </Table>
            </div>
        );

    }

    render() {
        return (
            <div className={"MainPageTop"}>
                <NavBarDw/>
                <div className={"MainPageMiddle"}>
                    <h1>Transacciones</h1>
                    {this.renderTransactions()}
                </div>
            </div>
        );
    }
}

export default withRouter(MainPage);