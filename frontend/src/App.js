import React from 'react';
import { BrowserRouter, Route, Switch, Redirect, NavLink } from 'react-router-dom';
import Login from './components/Login';
import ForgotPassword from './components/ForgotPassword';
import Profile from './components/Profile';
import Logout from './components/Logout';
import RegisterUser from './components/RegisterUser';
import ResetPassword from './components/ResetPassword';
import HomePage from './components/HomePage';

const PrivateRoute = ({ component: Component, ...rest }) => {
  const isAuthenticated = !!localStorage.getItem('user');
  return (
    <Route
      {...rest}
      render={props =>
        isAuthenticated ? (
          <Component {...props} />
        ) : (
          <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
        )
      }
    />
  );
};

function App() {
  const isAuthenticated = !!localStorage.getItem('user');

  return (
    <BrowserRouter>
      <div>
        <nav>
          {isAuthenticated ? (
            <>
              <NavLink to="/profile">Profile</NavLink>
              <p>

              <Logout /> </p>
            </>
          ) : (
            <>
              
             
            </>
          )}
        </nav>

        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/register" component={RegisterUser} />
          <Route path="/forgot-password" component={ForgotPassword} />
          <Route path="/reset-password" component={ResetPassword} />
          <PrivateRoute path="/profile" component={Profile} />
          <Route path="/" exact component={HomePage} />
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;