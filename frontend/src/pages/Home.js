import React from 'react';
import axios from 'axios';
import { v4 as uuidv4 } from 'uuid';


const Home = ({ ws }) => {

  const [email, setemail] = React.useState('');
  const [title, settitle] = React.useState('');
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

  const handleDelete = (id) => {
    const body = {
      post_id : id,
    }
    console.log(body);
    axios.post('/deleteListing', body)
    .then(fetchListings)
    .catch(e=>{
      console.log(e);
    });
  }


  const handleSubmit = () => {
    var date = new Date();
    date = date.toLocaleDateString();
    const body = {
      email: email,
      title: title,
      price: price,
      description: description,
      post_id: uuidv4(),
      date: date,
    };
    console.log(body);


    axios.post('/addListing', body)
      .then(console.log(body))
      .then(fetchListings)
      .catch(e => {
        console.log(e)
      })
    setemail('');
    settitle('');
    setprice('');
    setdescription('');

  };
  
  /***************************************************************************************/
  const [listings, setListings] = React.useState([]);
  const fetchListings = () => {
    axios.get('/viewListings') // asyc
      // http://www.json-generator.com/api/json/get/bIIhkxmgmW?indent=2
      // res is what the spark server sent back
      .then((res) => {
        console.log(res.data);
        setListings(res.data);

      });

  };

  var emailTo= "mailto: ";

  React.useEffect(() => {
    // Trigger only 1 time
    fetchListings();

    ws.addEventListener('message', (message) => {
      console.log("WEBSOCKET");
      console.log(message.data);
      const parsedData = JSON.parse(message.data);
      console.log(parsedData);
      setListings(parsedData);
    });
  }, []);

  return (
    <div class="d-flex">
      {/* ---------------------Homepage Displaying all listings--------------------- */}
      <div class="post-container mb-5 w-75">
        <small>Recent Posts</small>
        <div class="items" id="rows">
          {listings.map((Objects, i) => <div key={i} id="col">
            <div class="card card-len">
              <div class="card-body">
                <div class="d-flex w-100 web-title">
                  <h5 class="card-title ml-3 ">{Objects.title}</h5>	&nbsp;
                  <h5 class="d-flex justify-content-end">${Objects.price}</h5>
                </div>
                <h6 class="card-subtitle text-muted text-primary">Email: {Objects.email}</h6>
                <small>Date: {Objects.date}</small>
                <p class=" font-italic">{Objects.description}</p>
              </div>
              <div class="w-100 d-flex">
              <div class="d-flex p-2">
                <button class="btn text-primary"><a class="text-decoration-none" href ={emailTo + Objects.email}>Contact</a></button>
                </div>

              <div class="d-flex w-100 justify-content-end p-2">              
                <small class="text-md-end"><button type="submit" class="btn text-danger" onClick={()=>handleDelete(Objects.post_id)}>Delete</button></small>
              </div>
            </div>

            </div>
          </div>)}
        </div>
      </div>

      {/* ---------------------Create Listing Form--------------------- */}
      <div class="createListing border-top footer">
        <div class=" d-flex justify-content-center w-100">
          <form onSubmit={handleSubmit} class="p-3">
            <h5>Create Listing</h5>
            <input class="display-block p-2" type="email" id="input-email" name="email" value={email} onChange={handleEmail} placeholder="email" required></input><br></br>
            <input class="display-block p-2" type="text" id="input-title" name="title" value={title} onChange={handleTitle} placeholder="title" required></input><br></br>
            <input class="display-block p-2" type="text" id="input-price" name="price" value={price} onChange={handlePrice} placeholder="price" required ></input><br></br>
            <input class="display-block p-2" type="text" id="input-description" name="description" value={description} onChange={handleDescription} placeholder="description"></input><br></br>
            <br></br>
            <button class="btn btn-success mt-1 p-2 w-100" type="submit" id="submit" value="Submit" onClick={handleSubmit}>Submit</button>
          </form>
        </div>
        <div id="items">

        </div>
      </div>
    </div>
  );
};

export default Home;
