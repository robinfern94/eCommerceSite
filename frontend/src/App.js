import React from 'react';
import './App.css';
import { Switch, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import Create from './pages/Create';


const App = ({ws}) => {
  // todo, add more pages!
  return (
    <div class="container-fluid">
      <div class="" id="navbarNavAltMarkup">
        <nav class="navbar navbar-expand-lg nav-color">
        <Link to="/" class="navbar-brand"><h1 class="web-title font-color p-2">Parradise</h1></Link>
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 w-100 font-color">
            <li class="nav-item">
              <Link to="/" class="nav-link font-color"> Home </Link>
            </li>
            <li class="nav-item">
              <Link to="/Create" class="nav-link font-color"> Create Post</Link>
            </li>
          </ul>


        </nav>
      </div>
      <Switch>

        <Route path="/Create">
          <Create/>
        </Route>

        <Route path="/">
          <Home ws={ws}/>
        </Route>


      </Switch>
      <footer><br></br> </footer>
    </div>
  );
};

export default App;
