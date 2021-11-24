import React from 'react';
//import {useState, useEffect} from 'react';
import axios from 'axios';
import { v4 as uuidv4 } from 'uuid';




const Create = () => {

  const [email, setemail] = React.useState('');
  const [title, settitle] = React.useState('');
  const [type, settype] = React.useState('');
  const [price, setprice] = React.useState('');
  const [description, setdescription] = React.useState('');

  const handleEmail = (e) => {
    setemail(e.target.value);
  };

  const handleTitle = (e) => {
    settitle(e.target.value);
  };

  const handlePrice = (e) => {
    setprice(e.target.value);
  };

  const handleDescription = (e) => {
    setdescription(e.target.value);
  };


  const handleSubmit = () => {
    var date = new Date();
    date = date.toLocaleDateString();
    const body = {
      email: email,
      title: title,
      type: type,
      price: price,
      description: description,
      post_id: uuidv4(),
      date: date,
    };
    console.log(body);


    function fadeOutEffect() {

      let name = document.createElement('div');
      name.innerText = "Success";
      name.className = "alert alert-success flash-message";
      document.getElementById('flash-msg-container').appendChild(name);

      var fadeTarget = name;

      var fadeEffect = setInterval(function () {
        if (!fadeTarget.style.opacity) {
          fadeTarget.style.opacity = 1;
        }
        if (fadeTarget.style.opacity > 0) {
          fadeTarget.style.opacity -= 0.1;
        } else {
          clearInterval(fadeEffect);
          fadeTarget.parentNode.removeChild(fadeTarget);
        }
      }, 400);
    }

    axios.post('/addListing', body)
      .then(console.log(body))
      .catch(e => {
        console.log(e)
      })
    setemail('');
    settitle('');
    settype('');
    setprice('');
    setdescription('');
    fadeOutEffect();
  };

  React.useEffect(() => {
    // Trigger only 1 time

  }, []);


  return (
    <div class="createListing  footer">
      <div id="flash-msg-container"></div>
      <div class=" d-flex justify-content-center w-100">
        <form onSubmit={handleSubmit} class="p-5">
          <h5>Create Listing</h5>
          <input class="display-block p-2" type="email" id="input-email" name="email" value={email} onChange={handleEmail} placeholder="email" required></input><br></br>
          <input class="display-block p-2" type="text" id="input-title" name="title" value={title} onChange={handleTitle} placeholder="title" required></input><br></br>
          <input class="display-block p-2" type="text" id="input-price" name="price" value={price} onChange={handlePrice} placeholder="price" required ></input><br></br>
          <input class="display-block p-2" type="text" id="input-description" name="description" value={description} onChange={handleDescription} placeholder="description"></input><br></br>
          <br></br>
          <button class="btn btn-success p-2 w-100" type="submit" id="submit" value="Submit" onClick={handleSubmit}>Submit</button>
        </form>
      </div>
      <div id="items">

      </div>
    </div>
  );
};

export default Create;
