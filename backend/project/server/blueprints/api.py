# project/server/user/views.py


#################
#### imports ####
#################
import time
from flask import render_template, Blueprint, url_for, \
    redirect, flash, request, jsonify

from project.server import db
from project.server import models

################
#### config ####
################

api = Blueprint('user', __name__, )


################
#### routes ####
################

@api.route('/categories')
def categories():
    categories = models.Category.query.all()
    response = {
        'status': 200,
        'success': True,
        'data': [c.json for c in categories]
    }

    return jsonify(response), 200


@api.route('/stores')
def stores():
    stores = models.Store.query.all()
    response = {
        'status': 200,
        'success': True,
        'data': [s.json for s in stores]
    }

    return jsonify(response), 200


@api.route('/deals/<int:category_id>')
def deals(category_id):
    deals = models.Deal.query.filter(models.Deal.start_time > time.time()).all()
    filtered_deals = filter(lambda deal: deal.store.category_id == category_id, deals)
    response = {
        'status': 200,
        'success': True,
        'data': [d.json for d in filtered_deals]
    }

    return jsonify(response), 200


@api.route('/nearby_deals/<string:location_id>')
def nearby_deals(location_id):
    deals = models.Deal.query.filter(models.Deal.start_time > time.time()).all()
    filtered_deals = filter(lambda deal: deal.store.location_id == location_id, deals)
    response = {
        'status': 200,
        'success': True,
        'data': [d.json for d in filtered_deals]
    }

    return jsonify(response), 200
