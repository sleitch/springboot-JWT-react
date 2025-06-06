import React from 'react';
import { BrowserRouter, Route, Switch, Redirect, NavLink } from 'react-router-dom';
import Login from './components/Login';
import ForgotPassword from './components/ForgotPassword';
import Profile from './components/Profile';
import Logout from './components/Logout';
import RegisterUser from './components/RegisterUser';
import ResetPassword from './components/ResetPassword';

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
                  <NavLink to="/login">Login</NavLink>

                  {'\u00A0'}                  <NavLink to="/register">Register</NavLink>
                  {'\u00A0'}
                  <NavLink to="/profile">Profile (log in)</NavLink>

             
            </>
          )}
        </nav>

        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/register" component={RegisterUser} />
          <Route path="/forgot-password" component={ForgotPassword} />
          <Route path="/reset-password" component={ResetPassword} />
          <PrivateRoute path="/profile" component={Profile} />
          <Route path="/" exact>
            <h1>Welcome to User One</h1>
          </Route>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;