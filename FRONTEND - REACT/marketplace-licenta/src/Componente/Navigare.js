import React, { Component } from "react";
import "./Navigare.css";
import { Link } from "react-router-dom";
import logo from "../logo2.jpg";

import { withRouter } from "react-router-dom";

class Navigare extends Component {
  state = { inputField: "" };

  updateInputChange = event => {
    this.setState({ inputField: event.target.value });
  };

  search = event => {
    event.preventDefault();
    const { inputField } = this.state;
    this.props.history.push(`/searchProduse/${inputField}`);
    this.setState({ inputField: "" });
  };

  deconectare = event => {
    localStorage.removeItem("token");
    event.preventDefault();
    this.props.history.push("/");
  };

  render() {
    const { inputField } = this.state;
    const token = localStorage.getItem("token");
    return (
      <nav className="navigation-bar">
        <div className="navigare-container-mare">
          <table cellPadding="0" cellSpacing="0">
            <tbody>
              <tr>
                <td className="col1">
                  <Link to="/">
                    <img
                      className="navigation-logo"
                      src={logo}
                      alt="logo aplicatie"
                    />
                  </Link>
                  <div className="dropdown-select-category">
                    <button className="dropbutton">
                      Navigheaza dupa categorie
                    </button>

                    <div className="lista-categorii">
                      <Link
                        to={`/categorieProduse/Studio & Inregistrari`}
                        className="link-categorie"
                      >
                        Studio & Inregistrari
                      </Link>
                      <Link
                        to={`/categorieProduse/Chitare`}
                        className="link-categorie"
                      >
                        Chitara
                      </Link>
                      <Link
                        to={`/categorieProduse/Bas`}
                        className="link-categorie"
                      >
                        Bas
                      </Link>
                      <Link
                        to={`/categorieProduse/Clape & Sintetizatoare`}
                        className="link-categorie"
                      >
                        Clape & Sintetizatoare
                      </Link>
                      <Link
                        to={`/categorieProduse/Tobe & Percutie`}
                        className="link-categorie"
                      >
                        Tobe & Percutie
                      </Link>
                      <Link
                        to={`/categorieProduse/Microfoane & Wireless`}
                        className="link-categorie"
                      >
                        Microfoane & Wireless
                      </Link>
                      <Link
                        to={`/categorieProduse/Sunet & Lumini`}
                        className="link-categorie"
                      >
                        Sunet & Lumini
                      </Link>
                      <Link
                        to={`/categorieProduse/DJ & Electronice`}
                        className="link-categorie"
                      >
                        DJ & Electronice
                      </Link>
                      <Link
                        to={`/categorieProduse/Altele`}
                        className="link-categorie"
                      >
                        Altele
                      </Link>
                    </div>
                  </div>
                </td>
                <td className="col2">
                  <form className="search" onSubmit={this.search}>
                    <input
                      value={inputField}
                      onChange={this.updateInputChange}
                      type="text"
                      placeholder="Cauta un produs..."
                      className="search-input"
                    />
                    <button type="submit" className="submit">
                      Cauta
                    </button>
                  </form>
                </td>
                <td className="col3">
                  <div className="div-contul-meu">
                    {token ? (
                      <div className="divstangacont">
                        <div className="divsstangacont">
                          <Link to="/login" className="linkCont">
                            Contul meu
                          </Link>
                        </div>
                        <div className="divdreaptacont">
                          <a
                            className="buton-logout"
                            onClick={this.deconectare}
                          >
                            <img
                              src="/img/logout.png"
                              alt="logOutIcon"
                              className="imagineLogOut"
                            />
                          </a>
                        </div>
                      </div>
                    ) : (
                      <div className="divstangacont">
                        <div className="divsstangacont">
                          <Link to="/login" className="linkCont">
                            Log in
                          </Link>
                        </div>
                      </div>
                    )}
                  </div>

                  <div className="div-lista-dorinte">
                    <Link to="/listaDorinte" className="linkCont">
                      Lista dorinte
                    </Link>
                  </div>
                  <br />
                  <br />
                  <Link to={`/vindeProdus`}>
                    <button className="button3">Vinde</button>
                  </Link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <hr className="linie-orizontala" />
      </nav>
    );
  }
}

export default withRouter(Navigare);
