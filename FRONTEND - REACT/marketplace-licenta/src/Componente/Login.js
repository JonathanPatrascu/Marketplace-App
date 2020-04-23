import React, { Component } from "react";
import "./Login.css";
import { Link } from "react-router-dom";

class Login extends Component {
  state = { error: false };
  login = event => {
    event.preventDefault();
    const {
      username: { value: username },
      parola: { value: parola }
    } = event.target;
    fetch("http://localhost:8888/utilizator/login", {
      method: "POST",
      body: JSON.stringify({
        username,
        parola
      }),
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then(res => {
        if (res.status) {
          return Promise.reject("status found");
        }
        const { token, ...user } = res;
        localStorage.setItem("user", JSON.stringify(user));
        localStorage.setItem("token", token);
        this.setState({ error: false });
        this.props.history.push("/contulMeu");
      })
      .catch(err => {
        this.setState({ error: true });
      });
  };
  componentWillMount() {
    if (localStorage.getItem("token")) {
      this.props.history.push("/contulMeu");
    }
  }
  render() {
    const { error } = this.state;
    return (
      <div className="login-div">
        <form onSubmit={this.login}>
          <h2 className="titlu">Logare / Creare Cont</h2>
          <input type="text" name="username" placeholder="Username" required />
          <input type="password" name="parola" placeholder="Parola" required />
          {error && <div className="error">Can't log you in</div>}
          <input type="submit" value="Login" className="buton-inregistrare" />

          <div className="bottom-row">
            Nu esti inregistrat?{" "}
            <Link to="creareCont" className="link-to">
              Creeaza un cont
            </Link>
          </div>
        </form>
      </div>
    );
  }
}

export default Login;
