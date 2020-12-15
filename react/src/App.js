import React from 'react';
import './App.css';
import {Route, Switch} from 'react-router';
import {BrowserRouter} from 'react-router-dom';
import Login from './Login';
import Register from './Register';
import MainPage from './MainPage';
import Transfer from './Transfer';
import CashIn from './CashIn';
import Profile from './Profile';

function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/mainpage" render={props => <MainPage user={props.location.state}/>}/>
                <Route path="/register" render={props => <Register user={props.location.state}/>}/>
                <Route path="/transfer" render={props => <Transfer user={props.location.state}/>}/>
                <Route path="/cashIn" render={props => <CashIn user={props.location.state}/>}/>
                <Route path="/profile" render={props => <Profile user={props.location.state}/>}/>
                <Route path="/" render={props => <Login {...props} />}/>
            </Switch>
        </BrowserRouter>

    );
}

export default App;
