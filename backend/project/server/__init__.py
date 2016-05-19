#################
#### imports ####
#################

import os

from flask import Flask, jsonify
from flask.ext.debugtoolbar import DebugToolbarExtension
from flask.ext.sqlalchemy import SQLAlchemy


################
#### config ####
################
from project.server import config

app = Flask(
    __name__,
    # template_folder='../client/templates',
    # static_folder='../client/static'
)


# app_settings = os.getenv('APP_SETTINGS', 'project.server.config.DevelopmentConfig')
# app.config.from_object(app_settings)
app.config.from_object(config.DevelopmentConfig)

####################
#### extensions ####
####################

toolbar = DebugToolbarExtension(app)
db = SQLAlchemy(app)


###################
### blueprints ####
###################

from project.server.blueprints.api import api
app.register_blueprint(api, url_prefix='/api')


########################
#### error handlers ####
########################

@app.errorhandler(403)
def forbidden_page(error):
    response = {
        'status': 403,
        'success': False,
        'description': 'Server error: %s' % error.message
    }

    return jsonify(response), 403


@app.errorhandler(404)
def not_found(error):
    response = {
        'status': 404,
        'success': False,
        'description': 'Resource not found!'
    }

    return jsonify(response), 404


@app.errorhandler(Exception)
def server_error(error):
    response = {
        'status': 500,
        'success': False,
        'description': 'Server error: %s' % error.message
    }

    return jsonify(response), 500
